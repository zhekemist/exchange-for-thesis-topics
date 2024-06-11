import {alertErrorHandler, responseHandler} from "./utils.mjs";


function userFromJson(userJSON) {
    const idLink = userJSON['_links']['self']['href'];
    let name = userJSON['name']['firstName'] + " " + userJSON['name']['lastName'];
    const isStudent = userJSON['userType'] === 'STUDENT';
    if (isStudent) {
        name += " (Student)"
    } else {
        name += " (Instructor)"
    }
    return {
        idLink: idLink,
        name: name,
        isStudent: isStudent
    }
}

const STUDENTS_URL = 'http://localhost:8080/api/students?size=4';
const INSTRUCTORS_URL = 'http://localhost:8080/api/instructors?size=4'


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
                const users = requestUsers([STUDENTS_URL, INSTRUCTORS_URL]);
                users.then(users => {
                    this._users.push(...users);
                    this._currentUser = 0;
                }).catch(alertErrorHandler("Loading of users failed."));
            }
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
        }
    }
}
