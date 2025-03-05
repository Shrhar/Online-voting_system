const backendUrl = "http://192.168.0.132:8080";
// Register new user
document.getElementById('registerForm')?.addEventListener('submit', async (event) => {
    event.preventDefault();
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const user = { email, password };

    const response = await fetch(`${backendUrl}/auth/register`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(user),
    });

    const data = await response.json();
    alert(data.message || "Registration Successful");
    if (response.ok) {
        window.location.href = 'login.html';  // Redirect to login after successful registration
    }
});

// Login existing user
document.getElementById('loginForm')?.addEventListener('submit', async (event) => {
    event.preventDefault();
    const email = document.getElementById('email').value;
    const password = document.getElementById('password').value;

    const loginRequest = { email, password };

    const response = await fetch(`${backendUrl}/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(loginRequest),
    });

    const data = await response.json();
    alert(data.message || "Login Successful");
    if (response.ok) {
        localStorage.setItem('userEmail', email);  // Store email to identify user for voting
        window.location.href = 'vote.html';  // Redirect to the vote page after login
    }
});

// Fetch candidates and display them
async function loadCandidates() {
    const response = await fetch(`${backendUrl}/candidates/all`);
    const candidates = await response.json();

    const candidateList = document.getElementById('candidateList');
    candidates.forEach(candidate => {
        const candidateDiv = document.createElement('div');
        candidateDiv.innerHTML = `
            <input type="radio" name="candidate" value="${candidate.id}" id="candidate${candidate.id}">
            <label for="candidate${candidate.id}">${candidate.name}</label>
        `;
        candidateList.appendChild(candidateDiv);
    });

    document.getElementById('voteButton').style.display = 'block';  // Show vote button
}

// Cast vote
document.getElementById('voteButton')?.addEventListener('click', async () => {
    const selectedCandidate = document.querySelector('input[name="candidate"]:checked');
    if (!selectedCandidate) {
        alert('Please select a candidate.');
        return;
    }

    const candidateId = selectedCandidate.value;
    const userEmail = localStorage.getItem('userEmail');
    
    if (!userEmail) {
        alert('You must log in first.');
        window.location.href = 'login.html';  // Redirect to login if user is not logged in
        return;
    }

    // Fetch userId based on the email stored in localStorage
    const userResponse = await fetch(`${backendUrl}/auth/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ email: userEmail, password: 'password' }),  // Assuming password is available or you will use a token
    });

    const userData = await userResponse.json();
    if (!userResponse.ok) {
        alert(userData.message || "Login failed");
        return;
    }

    const userId = userData.id;  // Assuming `id` is returned after login success

    // Cast the vote
    const voteResponse = await fetch(`${backendUrl}/votes/cast/${candidateId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ userId }),
    });

    const voteData = await voteResponse.json();
    alert(voteData.message || "Vote Casted Successfully");
    if (voteResponse.ok) {
        window.location.href = 'results.html';  // Redirect to results page after successful vote
    }
});

// Load candidates if on the vote page
if (window.location.pathname === '/vote.html') {
    loadCandidates();
}

// Display results on the results page
async function loadResults() {
    const response = await fetch(`${backendUrl}/votes/results`);
    const candidates = await response.json();

    const resultDiv = document.getElementById('resultList');
    candidates.forEach(candidate => {
        const candidateDiv = document.createElement('div');
        candidateDiv.innerHTML = `${candidate.name} - ${candidate.votes} votes`;
        resultDiv.appendChild(candidateDiv);
    });
}

// Call this function when results page is loaded
if (window.location.pathname === '/results.html') {
    loadResults();
}
