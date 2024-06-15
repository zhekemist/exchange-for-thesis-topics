import {alertErrorHandler, responseHandler} from "./utils.mjs";
import {requestCategories} from "./topics.mjs";
import {CATEGORIES_ENPOINT, TOPIC_EXISTS_ENDPOINT, TOPICS_ENDPOINT} from "./constants.mjs";

async function queryTitleExistence(title) {
    const url = `${TOPIC_EXISTS_ENDPOINT}?title=${encodeURIComponent(title)}`;
    return fetch(url, {mode: "cors"}).then(responseHandler);
}

function stripDownInstructor(instructor) {
    return {
        name: instructor.name,
        contactInformation: instructor.contactInformation,
        researchGroup: instructor.researchGroup
    }
}

async function sendTopic(supervisor, title, description, categories, references) {
    const topic = {
        title: title,
        description: description,
        categories: Object.values(categories),
    }

    if (Alpine.store("api-version").isMariaApi) {
        topic.supervisor = supervisor.idLink;
        topic.categories = topic.categories.map(category => category.idLink);
        topic.references = references;
    } else {
        topic.instructor = stripDownInstructor(supervisor.originalObject);
        topic.literatureReference = Object.values(references);
    }


    const topicBody = JSON.stringify(topic);
    return fetch(TOPICS_ENDPOINT, {
        method: 'POST',
        mode: "cors",
        headers: {'Content-Type': 'application/json'},
        body: topicBody
    });
}


export function createTopicCreationForm() {
    return {
        form: null,

        title: "",
        titleSelected: false,
        titleInvalid: false,

        description: "",

        categorySearch: "",
        categoryResults: null,
        selectedCategories: {},

        referenceTitle: null,
        referenceYear: null,
        referenceAuthor: null,
        referenceLink: null,

        referenceCounter: 0,
        referenceModal: new bootstrap.Modal(document.getElementById('referencesModal')),
        literatureReferences: {},

        validateTitle() {
            if (this.title.length === 0) {
                return;
            }
            queryTitleExistence(this.title).then(exist => {
                if (!exist) {
                    this.titleSelected = true;
                    this.titleInvalid = false;
                } else {
                    this.titleInvalid = true;
                }
            }).catch(alertErrorHandler("Couldn't validate the title."));
        },

        searchCategories() {
            const url = `${CATEGORIES_ENPOINT}/search/name?name=${this.categorySearch}`;
            requestCategories(url).then(categories => {
                this.categoryResults = Object.fromEntries(categories.map(category => [category.idLink, category]));
            }).catch(alertErrorHandler("Failed to search for categories."));
        },

        clearSearch() {
            this.categorySearch = "";
            this.categoryResults = null;
        },

        selectCategory(categoryIdLink) {
            this.selectedCategories[categoryIdLink] = this.categoryResults[categoryIdLink];
            delete this.categoryResults[categoryIdLink];
        },

        unselectCategory(categoryIdLink) {
            delete this.selectedCategories[categoryIdLink];
        },

        saveReference() {
            const form = document.getElementById("referenceForm");
            if (form.checkValidity()) {
                this.literatureReferences[this.referenceCounter++] = {
                    title: this.referenceTitle,
                    author: this.referenceAuthor || null,
                    year: Number.parseInt(this.referenceYear) || null,
                    link: this.referenceLink || null,
                };
                this.referenceModal.hide();
            }
        },

        deleteReference(number) {
            delete this.literatureReferences[number];
        },

        submitTopic(supervisor) {
            if (this.description && this.form.checkValidity()) {
                sendTopic(supervisor, this.title, this.description, this.selectedCategories, this.literatureReferences).then(
                    response => {
                        if (response.ok) {
                            window.alert("Topic submitted successfully!");
                            window.location.href = "index.html";
                        } else {
                            throw Error("Topic POST response was not okay.");
                        }
                    }
                ).catch(alertErrorHandler("Failed to submit the topic."))
            }
        }
    }
}
