export const GetMethod = (url: string, callback: (status: number, response: any) => void): void => {
    const getRequest = new XMLHttpRequest();
    getRequest.responseType = 'json';

    getRequest.onreadystatechange = () => {
        if (getRequest.readyState === XMLHttpRequest.DONE) {
            callback(getRequest.status, getRequest.response);
        }
    };

    getRequest.open("GET", url, true);
    getRequest.setRequestHeader("Content-type", "application/json");
    getRequest.send();
};

export const PostMethod = (
    url: string,
    params: Document | XMLHttpRequestBodyInit | null,
    callback?: (status: number, response: any) => void
): void => {
    const postRequest = new XMLHttpRequest();
    postRequest.responseType = 'json';

    postRequest.onreadystatechange = () => {
        if (postRequest.readyState === XMLHttpRequest.DONE && callback) {
            callback(postRequest.status, postRequest.response);
        }
    };

    postRequest.open("POST", url, true);
    postRequest.setRequestHeader("Content-type", "application/json");
    postRequest.send(params);
};

export const DeleteMethod = (
    url: string,
    params: Document | XMLHttpRequestBodyInit | null,
    callback?: (status: number, response: any) => void
): void => {
    const deleteRequest = new XMLHttpRequest();
    deleteRequest.responseType = 'json';

    deleteRequest.onreadystatechange = () => {
        if (deleteRequest.readyState === XMLHttpRequest.DONE && callback) {
            callback(deleteRequest.status, deleteRequest.response);
        }
    };

    deleteRequest.open("DELETE", url, true);
    deleteRequest.setRequestHeader("Content-type", "application/json");
    deleteRequest.send(params);
};
