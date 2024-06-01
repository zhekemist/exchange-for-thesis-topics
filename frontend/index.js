window.addEventListener('pageshow', function(event) {
    if (event.persisted || window.performance && window.performance.navigation.type === 2) {
        window.location.reload();
    }
});

document.addEventListener('alpine:init', () => {
    Alpine.data('userManager', function () {
        return {
            users: [
                // 'Wolfgang Klaas',
                // 'Ralph Vigne',
                // 'Peter Nachname'
            ],
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
                    console.log(students[0]._links.choices.href)

                    for(let i=0; i<students.length; i++) {
                        console.log(students[i]);
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
                            console.log(studentChoicesData);

                            const topicChoicesData = studentChoicesData._embedded.topicChoices;
                            for(let k=0; k<topicChoicesData.length;k++){
                                priorityPoints = priorityPoints - topicChoicesData[k].priorityPoints;
                            }

                        } catch (error) {
                            console.log(error)
                        }

                        this.users.push({ name: name, matriculationNumber: matriculationNumber, email: email, studyProgram: studyProgram, username: username, userType: userType, priorityPoints: priorityPoints })
                    }

                } catch (error) {
                    console.log(error)
                }

                try {
                    const response = await fetch("http://localhost:8080/api/instructors?size=4", {mode: 'cors'});

                    if (!response.ok)
                        throw new Error("Topics data could not be fetched");

                    const instructorData = await response.json();
                    console.log(instructorData._embedded.instructors);
                    const instructors = instructorData._embedded.instructors;
                    console.log(instructors[0]._links)

                    for(let i=0; i<instructors.length; i++) {
                        const name = instructors[i].name.firstName + " " + instructors[i].name.lastName;
                        let email = instructors[i].email;
                        const username = instructors[i].username;
                        const userType = instructors[i].userType;
                        const administrator = instructors[i].administrator;
                        let contactInformation = instructors[i].contactInformation

                        this.users.push({ name: name, email: email, username: username, userType: userType, administrator: administrator, contactInformation: contactInformation })
                    }

                } catch (error) {
                    console.log(error)
                }

                this.dataAvailable = true;
                console.log(this.users);
                console.log(this._currentUser);
            }
        }
    })

    Alpine.data('thesisTopicData', function () {
        return {
            topics: [
                // {
                //     id: 1,
                //     title: "Machine Learning",
                //     instructor: "Dr. Müller",
                //     description: "Introduction to machine learning algorithms and applications."
                // },
                // {
                //     id: 2,
                //     title: "Data Science",
                //     instructor: "Prof. Schmidt",
                //     description: "Fundamentals of data analysis, visualization, and predictive modeling."
                // },
                // {
                //     id: 3,
                //     title: "Artificial Intelligence",
                //     instructor: "Dr. Meier",
                //     description: "Exploration of various AI techniques including neural networks and natural language processing."
                // },
                // {
                //     id: 4,
                //     title: "Web Development",
                //     instructor: "Dr. Smith",
                //     description: "Building dynamic and interactive websites using HTML, CSS, and JavaScript."
                // },
                // {
                //     id: 5,
                //     title: "Database Management",
                //     instructor: "Prof. Johnson",
                //     description: "Understanding and implementing database systems for efficient data storage and retrieval."
                // }
            ],

            activeTopicIndex: null,

            activeTopicModal: null,

            choicePriorityPoints: 0,

            addChoice () {
                console.log(this.choicePriorityPoints);
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

                        this.topics.push({title, instructor, description, categories});
                    }

                } catch (error) {
                    console.log(error)
                }
            }
        }


    });

});
