window.addEventListener('pageshow', function(event) {
    if (event.persisted || window.performance && window.performance.navigation.type === 2) {
        window.location.reload();
    }
});

document.addEventListener('alpine:init', () => {
    Alpine.data('userManager', function () {
        return {
            users: [],

            _currentUser: this.$persist(0),

            get currentUser() {
                return this.users[this._currentUser];
            },

            get currentUserPriorityPoints() {
                return this.users[this._currentUser];
            },

            set currentUser(newUser) {
                this._currentUser = newUser;
                console.log(this._currentUser);
            },

            dataAvailable: false,

            async fetchUserData() {
                try {
                    const response = await fetch("http://localhost:8080/api/students?size=4", {mode: 'cors'});

                    if (!response.ok)
                        throw new Error("Topics data could not be fetched");

                    const studentData = await response.json();
                    console.log(studentData._embedded.students);
                    const students = studentData._embedded.students;
                    // console.log(students[0]._links.choices.href)

                    for(let i=0; i<students.length; i++) {
                        // console.log(students[i]);
                        const name = students[i].name.firstName + " " + students[i].name.lastName;
                        const matriculationNumber = students[i].matriculationNumber;
                        let email = students[i].email;
                        let studyProgram = students[i].email;
                        const username = students[i].username;
                        const userType = students[i].userType;

                        let priorityPoints = 1000;
                        try {
                            const response = await fetch(students[i]._links.choices.href, {mode: 'cors'});

                            if (!response.ok)
                                throw new Error("Topic data could not be fetched");
                            const studentChoicesData = await response.json();
                            // console.log(studentChoicesData);

                            const topicChoicesData = studentChoicesData._embedded.topicChoices;
                            for(let k=0; k<topicChoicesData.length;k++){
                                priorityPoints = priorityPoints - topicChoicesData[k].priorityPoints;
                            }

                        } catch (error) {
                            console.log(error)
                        }

                        const link = students[i]._links.self.href;

                        this.users.push({ name: name, matriculationNumber: matriculationNumber, email: email, studyProgram: studyProgram, username: username, userType: userType, priorityPoints: priorityPoints, link: link })
                    }

                } catch (error) {
                    console.log(error)
                }

                try {
                    const response = await fetch("http://localhost:8080/api/instructors?size=4", {mode: 'cors'});

                    if (!response.ok)
                        throw new Error("Topics data could not be fetched");

                    const instructorData = await response.json();
                    // console.log(instructorData._embedded.instructors);
                    const instructors = instructorData._embedded.instructors;
                    // console.log(instructors[0]._links)

                    for(let i=0; i<instructors.length; i++) {
                        const name = instructors[i].name.firstName + " " + instructors[i].name.lastName;
                        let email = instructors[i].email;
                        const username = instructors[i].username;
                        const userType = instructors[i].userType;
                        const administrator = instructors[i].administrator;
                        let contactInformation = instructors[i].contactInformation
                        const link = instructors[i]._links.self.href;

                        this.users.push({ name: name, email: email, username: username, userType: userType, administrator: administrator, contactInformation: contactInformation, link: link })
                    }

                } catch (error) {
                    console.log(error)
                }

                this.dataAvailable = true;
                // console.log(this.users);
                // console.log(this._currentUser);
            },








            topics: [],

            activeTopicIndex: null,

            activeTopicModal: null,

            choicePriorityPoints: 0,

            getFormattedTimestamp() {
                const now = new Date();
                const year = now.getFullYear();
                const month = String(now.getMonth() + 1).padStart(2, '0');
                const day = String(now.getDate()).padStart(2, '0');
                const hours = String(now.getHours()).padStart(2, '0');
                const minutes = String(now.getMinutes()).padStart(2, '0');
                const seconds = String(now.getSeconds()).padStart(2, '0');

                return `${year}-${month}-${day}T${hours}:${minutes}:${seconds}`;
            },

            async addChoice() {
                console.log(this.topics[this.activeTopicIndex]);
                console.log(this.users[this._currentUser]);
                try {
                    const data = {
                        timestamp: this.getFormattedTimestamp(),
                        priorityPoints: this.choicePriorityPoints,
                        topic: this.topics[this.activeTopicIndex].link,
                        student: this.users[this._currentUser].link
                    };

                    console.log(JSON.stringify(data));

                    const response = await fetch("http://localhost:8080/api/topicChoices", {
                        method: 'POST',
                        headers: {
                            'Content-Type': 'application/json'
                        },
                        body: JSON.stringify(data) // Convert the data to a JSON string
                    });

                    // Check if the request was successful
                    if (!response.ok) {
                        throw new Error('Network response was not ok');
                    }

                    // Parse the JSON response
                    const responseData = await response.json();
                    console.log('Success:', responseData);
                } catch (error) {
                    console.error('Error:', error);
                }
            },

            async fetchTopicData() {
                try {
                    const response = await fetch("http://localhost:8080/api/thesisTopics", {mode: 'cors'});

                    if (!response.ok)
                        throw new Error("Topics data could not be fetched");

                    const thesisTopicData = await response.json();
                    // console.log(thesisTopicData._embedded.thesisTopics);
                    const thesisTopics = thesisTopicData._embedded.thesisTopics;
                    // console.log(thesisTopics[0]._links);

                    for (let i = 0; i < thesisTopics.length; i++) {
                        const title = thesisTopics[i].title;
                        // console.log(title);

                        const description = thesisTopics[i].description;
                        // console.log(description);

                        let categories = [];
                        try {
                            const response = await fetch(thesisTopics[i]._links.categories.href, {mode: 'cors'});

                            if (!response.ok)
                                throw new Error("Topic data could not be fetched");
                            const thesisTopicCategoryData = await response.json();
                            // console.log(thesisTopicCategoryData);

                            const categoriesData = thesisTopicCategoryData._embedded.categories;
                            for (let k = 0; k < categoriesData.length; k++) {
                                const name = categoriesData[k].name;
                                const shortDescription = categoriesData[k].shortDescription;

                                categories.push({name: name, shortDescription: shortDescription});
                            }

                        } catch (error) {
                            console.log(error)
                        }
                        // console.log(categories);


                        let instructor = null;
                        try {
                            const response = await fetch(thesisTopics[i]._links.supervisor.href, {mode: 'cors'});

                            if (!response.ok)
                                throw new Error("Topic data could not be fetched");
                            const thesisTopicInstructorData = await response.json();
                            // console.log(thesisTopicInstructorData);
                            const name = thesisTopicInstructorData.name.firstName + " " + thesisTopicInstructorData.name.lastName;
                            let contactInformation = thesisTopicInstructorData.contactInformation;
                            let email = thesisTopicInstructorData.email;
                            // Forschungsgruppe noch ergänzen
                            instructor = {name: name, contactInformation: contactInformation, email: email};
                        } catch (error) {
                            console.log(error)
                        }
                        // console.log(instructor);

                        // references =[]
                        // console.log(thesisTopics[i].references.length);
                        // for(let k=0; k<thesisTopics[i].references.length; k++){
                        //     reference = thesisTopics[i].references[k];
                        //     console.log(reference);
                        //     references.push({ title: reference.title});
                        // }
                        // console.log(references);

                        const link = thesisTopics[i]._links.self.href;

                        this.topics.push({title: title, instructor: instructor, description: description, categories: categories, link: link});
                    }

                } catch (error) {
                    console.log(error)
                }
            },

            userChoices: [],

            getIdFromURL (studentURL){
                let id = '';
                let i = studentURL.length;
                while (i>=0 && !isNaN(studentURL[i-1])) {
                    id = studentURL[i-1] + id;
                    i = i-1;
                }
                return id;
            },

            wait(ms) {
                return new Promise(resolve => setTimeout(resolve, ms));
            },

            async fetchUserChoicesData() {
                console.log("fetchUserChoicesData");
                try {
                    while(!this.dataAvailable){await this.wait(1);}

                    const studentIndex = this.getIdFromURL(this.users[this._currentUser].link);
                    console.log(studentIndex);
                    console.log(`http://localhost:8080/api/students/${studentIndex}/choices`);
                    const response = await fetch(`http://localhost:8080/api/students/${studentIndex}/choices`, { mode: 'cors' });

                    if (!response.ok)
                        throw new Error("Topics data could not be fetched");

                    const userChoicesData = await response.json();
                    console.log(userChoicesData._embedded.topicChoices);
                    console.log("here");
                    const userChoices = userChoicesData._embedded.topicChoices;
                    // console.log(userChoices[0]._links.choices.href);

                    for(let i=0; i<userChoices.length; i++) {
                        console.log(userChoices[i]);

                        const priorityPoints = userChoices[i].priorityPoints;

                        try {
                            const response = await fetch(userChoices[i]._links.topic.href, {mode: 'cors'});

                            if (!response.ok)
                                throw new Error("Topics data could not be fetched");

                            const chosenTopicData = await response.json();
                            console.log(chosenTopicData);


                            const title = chosenTopicData.title;
                            // console.log(title);

                            const description = chosenTopicData.description;
                            // console.log(description);

                            let categories = [];
                            try {
                                const response = await fetch(chosenTopicData._links.categories.href, {mode: 'cors'});

                                if (!response.ok)
                                    throw new Error("Topic data could not be fetched");
                                const thesisTopicCategoryData = await response.json();
                                // console.log(thesisTopicCategoryData);

                                const categoriesData = thesisTopicCategoryData._embedded.categories;
                                for (let k = 0; k < categoriesData.length; k++) {
                                    const name = categoriesData[k].name;
                                    const shortDescription = categoriesData[k].shortDescription;

                                    categories.push({name: name, shortDescription: shortDescription});
                                }
                            } catch (error) {
                                console.log(error)
                            }

                            let instructor = null;
                            try {
                                const response = await fetch(chosenTopicData._links.supervisor.href, {mode: 'cors'});

                                if (!response.ok)
                                    throw new Error("Topic data could not be fetched");
                                const thesisTopicInstructorData = await response.json();
                                // console.log(thesisTopicInstructorData);
                                const name = thesisTopicInstructorData.name.firstName + " " + thesisTopicInstructorData.name.lastName;
                                let contactInformation = thesisTopicInstructorData.contactInformation;
                                let email = thesisTopicInstructorData.email;
                                // Forschungsgruppe noch ergänzen
                                instructor = {name: name, contactInformation: contactInformation, email: email};
                            } catch (error) {
                                console.log(error)
                            }

                            const link = chosenTopicData._links.self.href;

                            this.userChoices.push({title: title, instructor: instructor, description: description, categories: categories, link: link, priorityPoints: priorityPoints});


                        } catch (error) {
                            console.log(error)
                        }
                    }

                } catch (error) {
                    console.log(error)
                }


            }
        }
    })

    Alpine.data('thesisTopicData', function () {
        return {

        }


    });

});
