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
});