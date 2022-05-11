var exec = require('cordova/exec');


var ThisTest = {
    coolMethod: function (arg0, success, error) {
        console.log("teste");
        exec(success, error, 'testeplugin', 'new_activity', [arg0]);
    }
}

module.exports = ThisTest;