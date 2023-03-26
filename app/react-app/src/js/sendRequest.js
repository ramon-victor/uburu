import $ from 'jquery';

const sendRequest = (url, method, params) => {
    return $.ajax(url, {
        type: method,
        data: params
    });
}

export default sendRequest;
