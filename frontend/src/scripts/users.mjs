import {alertErrorHandler, responseHandler} from "./utils.mjs";
import {DEMO_INSTRUCTORS, DEMO_STUDENTS} from "./constants.mjs";


function userFromJson(userJSON) {
    const idLink = userJSON['_links']['self']['href'];
    let name = userJSON['name']['firstName'] + " " + userJSON['name']['lastName'];
    const isStudent = idLink.includes('students');
    if (isStudent) {
        name += " (Student)"
    } else {
        name += " (Instructor)"
    }
    return {
        idLink: idLink,
        name: name,
        isStudent: isStudent,
        topicChoices: userJSON['topicChoices']
    }
}

async function requestUser(idLink) {
    const response = await fetch(idLink, {mode: 'cors'}).then(responseHandler);
    return userFromJson(response);
}

function requestUsers(urlList) {
    const promises = []
    for (const url of urlList) {
        promises.push(fetch(url, {mode: "cors"})
            .then(responseHandler));
    }
    return Promise.all(promises).then((results) => results
        .map(result => {
            result = result['_embedded'];
            if (result.hasOwnProperty('students')) {
                return result.students;
            } else {
                return result.instructors;
            }
        }).flat().map(userFromJson))
}


export function createUserManager() {
    return {
        _users: this.$persist([]),
        _currentUser: this.$persist(0),

        init() {
            if (!this.usersLoaded) {
                const users = requestUsers([DEMO_STUDENTS, DEMO_INSTRUCTORS]);
                users.then(users => {
                    this._users.push(...users);
                    this._currentUser = 0;
                }).catch(alertErrorHandler("Loading of users failed."));
            }
            this.updateCurrentUser();
        },

        updateCurrentUser() {
            requestUser(this.currentUser.idLink).then(
                user => this._users[this._currentUser] = user
            );
        },

        get usersLoaded() {
            return this._users.length > 0;
        },

        get users() {
            return this._users;
        },

        get currentUser() {
            return this._users[this._currentUser];
        },

        set currentUser(user) {
            this._currentUser = user;
            this.updateCurrentUser();
        }
    }
}
