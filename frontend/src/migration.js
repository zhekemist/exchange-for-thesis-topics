import {MIGRATION_ENDPOINT, VERSION_ENDPOINT} from "./scripts/constants.mjs";
import {responseHandler} from "./scripts/utils.mjs";

window.onload = callMigrationEndpoint;

function callMigrationEndpoint() {
    console.log("Requesting migration ...")
    fetch(MIGRATION_ENDPOINT, {
        mode: "cors",
        method: "POST"
    }).then(reponse => {
        }
    ).catch(error => {
    });
}

document.addEventListener('alpine:init', () => {
    Alpine.data('countdown', createCoundown);
});

function createCoundown() {
    return {
        timeLeft: 10,
        timeoutHandled: false,
        decrementTime() {
            this.timeLeft--;
        },
        setTime(time) {
            this.timeLeft = time;
        },
        run() {
            setInterval(tick, 1000, this);
        }
    }
}

function tick(state) {
    if (state.timeLeft > 0) {
        state.decrementTime();
        return;
    }
    if (!state.timeoutHandled) {
        state.timeoutHandled = true;
        handleTimeout(state);
    }
}

function handleTimeout(state) {
    fetch(VERSION_ENDPOINT, {mode: 'cors'})
        .then(responseHandler)
        .then(version => {
            if (version === "MONGODB") {
                window.alert("Migration complete!");
                window.location.href = "index.html";
            } else {
                resetCountdown(state)
            }
        })
        .catch((error) => {
            resetCountdown(state);
        });
}

function resetCountdown(state) {
    state.setTime(10);
    state.timeoutHandled = false;
}
