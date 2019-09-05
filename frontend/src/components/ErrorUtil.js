
export function handleHttpErrors(response) {
    if (!response.ok) {
        throw Error("HTTP Response Code: "+response.status);
    }
    return response;
}