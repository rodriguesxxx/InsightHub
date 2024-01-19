const apiUrl = "http://localhost:8080/api/stats/rodriguesxxx";

async function updateData() {
    try {
        const response = await fetch(apiUrl);
        if (!response.ok) {
            throw new Error(`Erro na requisição: ${response.status}`);
        }

        const responseData = await response.json();

        if (responseData && responseData.data) {
            const data = responseData.data;
            document.getElementById("commits").textContent = data.commits;
            document.getElementById("issues").textContent = data.issues;
            document.getElementById("prs").textContent = data.pullRequests;
        }
    } catch (error) {
        console.error("Erro durante a atualização dos dados:", error);
    }
}

document.addEventListener("DOMContentLoaded", updateData);
