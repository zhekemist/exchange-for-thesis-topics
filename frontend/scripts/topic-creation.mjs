import {alertErrorHandler, responseHandler} from "./utils.mjs";
import {requestCategories} from "./topics.mjs";

const TOPICS_URL = "http://localhost:8080/api/thesisTopics";
const TOPIC_EXISTS_URL = "http://localhost:8080/api/thesisTopics/search/exists";

async function queryTitleExistence(title) {
    const url = `${TOPIC_EXISTS_URL}?title=${encodeURIComponent(title)}`;
    return fetch(url, {mode: "cors"}).then(responseHandler);
}

async function sendTopic(supervisorIdLink, title, description, categories, references) {
    const topic = {
        supervisor: supervisorIdLink,
        title: title,
        description: description,
        categories: Object.values(categories).map(category => category.idLink),
        references: references
    }
    const topicBody = JSON.stringify(topic);
    return fetch(TOPICS_URL, {
        method: 'POST',
        mode: "cors",
        headers: {'Content-Type': 'application/json'},
        body: topicBody
    });
}

const CATEGORIES_URL = "http://localhost:8080/api/categories";

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
            const url = `${CATEGORIES_URL}/search/name?name=${this.categorySearch}`;
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

        submitTopic(supervisorIdLink) {
            if (this.description && this.form.checkValidity()) {
                sendTopic(supervisorIdLink, this.title, this.description, this.selectedCategories, this.literatureReferences).then(
                    response => {
                        if (response.ok) {
                            window.alert("Topic submitted successfully!");
                            window.location.href = "profile.html";
                        } else {
                            throw Error("Topic POST response was not okay.");
                        }
                    }
                ).catch(alertErrorHandler("Failed to submit the topic."))
            }
        }
    }
}
