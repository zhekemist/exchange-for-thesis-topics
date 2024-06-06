import {createUserManager} from "./scripts/users.mjs";
import {createTopicChoicesData, getChoices} from "./scripts/choices.mjs";
import {requestTopics} from "./scripts/topics.mjs";

function getTopicsOfInstructor(instructorIdLink) {
    const url = `${instructorIdLink}/topics`;
    return requestTopics(url, false);
}

document.addEventListener('alpine:init', () => {
    Alpine.data('userManager', createUserManager);
    Alpine.data('topicChoices', createTopicChoicesData);
    Alpine.store('getChoices', getChoices);
    Alpine.store('getTopicsOfInstructor', getTopicsOfInstructor);
});
