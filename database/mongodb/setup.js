const collections = db.getCollectionNames().map(collectionName => db.getCollection(collectionName));
for (const collection of collections) {
    collection.drop();
}

db.thesisTopics.createIndex({title: 1}, {collation: {locale: "en", strength: 1}})
db.categories.createIndex({name: "text"})
db.students.createIndex({"assignedTopic.topic.instructor.researchGroup.name": 1})
db.students.createIndex({"assignedTopic.topic.title": 1})
