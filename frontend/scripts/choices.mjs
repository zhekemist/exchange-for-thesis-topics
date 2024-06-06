import {alertErrorHandler, getLink, responseHandler} from "./utils.mjs";
import {requestTopic} from "./topics.mjs";

function getFormattedTimestamp() {
    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0');
    const day = String(now.getDate()).padStart(2, '0');
    const hours = String(now.getHours()).padStart(2, '0');
    const minutes = String(now.getMinutes()).padStart(2, '0');
    const seconds = String(now.getSeconds()).padStart(2, '0');

    return `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;
}

const CHOICES_URL = 'http://localhost:8080/api/topicChoices';
const MAX_PRIORITY_POINTS = 1000;

export async function getRemainingPriorityPoints(studentIdLink) {
    const url = studentIdLink + "/priorityPoints";
    const response = await fetch(url, {mode: "cors"}).then(responseHandler);
    return MAX_PRIORITY_POINTS - response;
}

export async function getChoices(studentIdLink) {
    const url = studentIdLink + "/choices";
    const response = await fetch(url, {mode: "cors"}).then(responseHandler);
    const result = [];
    for (const topicChoiceJson of response['_embedded']['topicChoices']) {
        result.push({
            priorityPoints: topicChoiceJson['priorityPoints'],
            topicData: await requestTopic(getLink(topicChoiceJson, 'topic'))
        });
    }
    return result;
}

function addChoice(studentIdLink, topicIdLink, priorityPoints) {
    console.log(studentIdLink, topicIdLink, priorityPoints);
    const choice = {
        timestamp: getFormattedTimestamp(),
        priorityPoints: priorityPoints,
        topic: topicIdLink,
        student: studentIdLink
    };

    console.log(JSON.stringify(choice))

    return fetch(CHOICES_URL, {
        method: 'POST',
        mode: "cors",
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(choice) // Convert the data to a JSON string
    }).then(response => {
            if (!response.ok) throw Error("Response was not okay!");
        }
    );
}

export function addChoiceAndRedirect(studentIdLink, topicIdLink, priorityPoints, redirectUrl) {
    const request = addChoice(studentIdLink, topicIdLink, priorityPoints);
    request.then(
        (_) => {
            window.location.href = redirectUrl;
        }
    ).catch(alertErrorHandler("Failed to send topic choice to server"))
}

export function createTopicChoicesData(studentUrl) {
    return {
        topics: [],
        activeTopicIndex: null,

        init() {
            this.topics = getChoices(studentUrl);
        },

        get currentTopic() {
            return this.topics[this.activeTopicIndex];
        }
    }
}
