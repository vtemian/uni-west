var Game = function() {
    init: function(mainView) {
        this.mainView = mainView;
    },

    play: function() {
        this.buildTerrain();
    },

    buildTerrain: function() {
        console.log(this.mainView);
    }
}

return Game;
