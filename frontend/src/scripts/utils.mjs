export function alertErrorHandler(errorMessage) {
    return (error) => {
        console.log("An error occured: ", errorMessage, " The error was:", error);
        window.alert("Unfortunately an error occured: " + errorMessage);
    }
}

export function responseHandler(response) {
    if (!response.ok) {
        throw Error("Response Status is not ok!");
    } else {
        return response.json();
    }
}

export function getLink(obj, link) {
    return obj['_links'][link]['href']
}
