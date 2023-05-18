export const sendHttpRequest = (method, url, params = {}) => {
    const promise = new Promise((resolve, reject)=> {
        const xhr = new XMLHttpRequest();
        xhr.open(method, url);

        xhr.responseType = 'json';
        xhr.onload = () => { resolve(xhr.response) };

        xhr.send(params);
    });

    return promise;
};
