import {VERSION_ENDPOINT} from "./scripts/constants.mjs";
import {responseHandler} from "./scripts/utils.mjs";

document.addEventListener('alpine:init', () => {
    Alpine.store('api-version', {
        version: undefined,
        get isMongoApi() {
            return this.version === "MONGODB";
        },
        get isMariaApi() {
            return this.version === "MARIADB";
        },
        update(new_version) {
            this.version = new_version;
        }
    })
    const response = fetch(VERSION_ENDPOINT, {'mode': 'cors'}).then(responseHandler);
    response.then(version => Alpine.store('api-version').update(version));
});
