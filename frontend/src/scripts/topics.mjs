import {alertErrorHandler, getLink, responseHandler} from "./utils.mjs";

const TOPICS_URL = 'http://localhost:8080/api/thesisTopics'

export async function requestCategories(categoriesUrl) {
    const response = await fetch(categoriesUrl, {mode: "cors"}).then(responseHandler);
    return response['_embedded']['categories'].map((categoryJson) => {
        return {
            idLink: getLink(categoryJson, 'self'),
            name: categoryJson['name'],
            shortDescription: categoryJson['shortDescription']
        }
    });
}

async function requestSupervisor(supervisorUrl) {
    const response = await fetch(supervisorUrl, {mode: "cors"}).then(responseHandler);
    return {
        name: response['name']['firstName'] + " " + response['name']['lastName'],
        contactInformation: response['contactInformation'],
        email: response['emails']
    };
}

async function requestTopicDetails(topicJson, doSupervisorRequest = true) {
    return {
        idLink: getLink(topicJson, 'self'),
        title: topicJson['title'],
        instructor: doSupervisorRequest ? (await requestSupervisor(getLink(topicJson, 'supervisor'))) : null,
        description: topicJson['description'],
        categories: await requestCategories(getLink(topicJson, 'categories'))
    };
}

export function requestTopic(topicUrl) {
    return fetch(topicUrl, {mode: "cors"}).then(responseHandler).then(topic => requestTopicDetails(topic));
}

export function requestTopics(url = TOPICS_URL, doSupervisorRequest = true) {
    return fetch(url, {mode: "cors"})
        .then(responseHandler)
        .then(topics => Promise.all(topics['_embedded']['thesisTopics'].map(
            (topic) => requestTopicDetails(topic, doSupervisorRequest)
        )));
}

export function createTopicData() {
    return {
        topics: [],
        activeTopicIndex: null,
        activeTopicModal: null,

        init() {
            const topicsRequest = requestTopics();
            topicsRequest
                .then((topics) => this.topics.push(...topics))
                .catch(alertErrorHandler("Loading of topics failed!"));
        },

        get currentTopic() {
            return this.topics[this.activeTopicIndex];
        }
    }
}
