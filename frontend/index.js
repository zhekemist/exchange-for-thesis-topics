import {createUserManager} from "./scripts/users.mjs";
import {createTopicData} from "./scripts/topics.mjs";
import {addChoice, getRemainingPriorityPoints} from "./scripts/choices.mjs";

document.addEventListener('alpine:init', () => {
    Alpine.data('userManager', createUserManager);
    Alpine.data('topicData', createTopicData);
    Alpine.store('addChoice', addChoice);
    Alpine.store('getRemainingPriorityPoints', getRemainingPriorityPoints)
});
