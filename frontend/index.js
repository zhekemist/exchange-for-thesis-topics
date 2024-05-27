document.addEventListener('alpine:init', () => {
    Alpine.data('userManager', function(){
        return {
            users: [
                'Wolfgang Klaas',
                'Ralph Vigne',
                'Peter Nachname'
            ],
            _currentUser: this.$persist(0),
    
            get currentUser(){
                return this.users[this._currentUser];
            },
    
            set currentUser(newUser){
                this._currentUser = newUser;
            }
        }
    })

    Alpine.data('thesisTopicData', () => ({
        topics : [
            { id: 1, title: "Machine Learning", instructor: "Dr. Müller", description: "Introduction to machine learning algorithms and applications." },
            { id: 2, title: "Data Science", instructor: "Prof. Schmidt", description: "Fundamentals of data analysis, visualization, and predictive modeling." },
            { id: 3, title: "Artificial Intelligence", instructor: "Dr. Meier", description: "Exploration of various AI techniques including neural networks and natural language processing." },
            { id: 4, title: "Web Development", instructor: "Dr. Smith", description: "Building dynamic and interactive websites using HTML, CSS, and JavaScript." },
            { id: 5, title: "Database Management", instructor: "Prof. Johnson", description: "Understanding and implementing database systems for efficient data storage and retrieval." },
            { id: 6, title: "Mobile App Development", instructor: "Dr. Williams", description: "Creating mobile applications for iOS and Android platforms using native and hybrid frameworks." },
            { id: 7, title: "Cybersecurity", instructor: "Prof. Brown", description: "Protecting computer systems and networks from cyber attacks through security protocols and practices." },
            { id: 8, title: "Cloud Computing", instructor: "Dr. Taylor", description: "Exploring cloud-based technologies for scalable and flexible computing resources." },
            { id: 9, title: "Blockchain Technology", instructor: "Prof. Martinez", description: "Understanding the fundamentals of blockchain and its applications in various industries." },
            { id: 10, title: "Internet of Things (IoT)", instructor: "Dr. Lee", description: "Integration of devices, sensors, and software for interconnected IoT ecosystems." }
        ],

        activeTopicIndex: null, // Initialize an array with the length of topics and all elements set to false

        activeTopicModal: null

    }));

});

