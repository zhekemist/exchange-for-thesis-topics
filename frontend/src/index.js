import {createUserManager} from "./scripts/users.mjs";
import {createTopicData} from "./scripts/topics.mjs";
import {addChoiceAndRedirect, checkIfChoiceAlreadyExists, getRemainingPriorityPoints} from "./scripts/choices.mjs";
import {createReportInformation} from "./scripts/reports.mjs";

document.addEventListener('alpine:init', () => {
    Alpine.data('userManager', createUserManager);
    Alpine.data('topicData', createTopicData);
    Alpine.data('reportInformation', createReportInformation)
    Alpine.store('addChoiceAndRedirect', addChoiceAndRedirect);
    Alpine.store('getRemainingPriorityPoints', getRemainingPriorityPoints);
    Alpine.store('checkIfChoiceAlreadyExists', checkIfChoiceAlreadyExists);
});
