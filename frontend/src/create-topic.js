import {createUserManager} from "./scripts/users.mjs";
import {createTopicCreationForm} from "./scripts/topic-creation.mjs";

document.addEventListener('alpine:init', () => {
    Alpine.data('userManager', createUserManager);
    Alpine.data('topicCreationForm', createTopicCreationForm);
});
