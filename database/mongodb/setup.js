const collections = db.getCollectionNames().map(collectionName => db.getCollection(collectionName));
for (const collection of collections) {
    collection.drop();
}

// TODO: Creation of Indices
