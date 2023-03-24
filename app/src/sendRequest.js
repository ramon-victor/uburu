import $ from 'jquery';

const sendRequest = (url, method, params) => {
    return $.ajax(url, {
        type: method,
        data: params
    });
}

/*
Exemplo de utilização:

import sendRequest from './sendRequest';

sendRequest('url', 'POST', {
    string: "string",
    int: 10
}).then((success) => {
    if (success) {
        console.log(success);
    }
});
*/

export default sendRequest;
