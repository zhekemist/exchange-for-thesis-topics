const HOST = "http://localhost:8080"

export const API_BASE_URL = `${HOST}/api`
export const TOPICS_ENDPOINT = `${API_BASE_URL}/thesisTopics`
export const TOPIC_EXISTS_ENDPOINT = `${TOPICS_ENDPOINT}/search/exists`
export const CHOICES_ENDPOINT = `${API_BASE_URL}/topicChoices`
export const CATEGORIES_ENPOINT = `${API_BASE_URL}/categories`;

export const DEMO_STUDENTS = `${API_BASE_URL}/students?size=4`
export const DEMO_INSTRUCTORS = `${API_BASE_URL}/instructors?size=4`

export const META_API_BASE_URL = `${HOST}/api-meta`
export const VERSION_ENDPOINT = `${META_API_BASE_URL}/version`
export const MIGRATION_ENDPOINT = `${META_API_BASE_URL}/migrate`
