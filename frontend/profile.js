import {createUserManager} from "./scripts/users.mjs";
import {createTopicChoicesData, getChoices} from "./scripts/choices.mjs";

document.addEventListener('alpine:init', () => {
    Alpine.data('userManager', createUserManager);
    Alpine.data('topicChoices', createTopicChoicesData);
    Alpine.store('getChoices', getChoices);
});
