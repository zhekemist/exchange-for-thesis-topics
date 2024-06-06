import {createUserManager} from "./scripts/users.mjs";
import {createTopicChoicesData} from "./scripts/choices.mjs";

document.addEventListener('alpine:init', () => {
    Alpine.data('userManager', createUserManager);
    Alpine.data('topicChoices', createTopicChoicesData);
});
