document.addEventListener('DOMContentLoaded', function () {
    // Card/session logic
    const prevBtn = document.getElementById('prevBtn');
    const nextBtn = document.getElementById('nextBtn');
    const shuffleBtn = document.getElementById('shuffleBtn');
    const cardTitle = document.getElementById('cardTitle');
    const cardContent = document.getElementById('cardContent');
    const progressText = document.getElementById('progressText');

    let allQuestions = [];
    let sessionQuestions = [];
    let index = 0;
    let asked = 0;
    const sessionSize = 20;

    function shuffleArray(a){
        for(let i=a.length-1;i>0;i--){
            const j=Math.floor(Math.random()*(i+1));
            [a[i],a[j]]=[a[j],a[i]];
        }
    }

    function renderCard(){
        if(!sessionQuestions || sessionQuestions.length===0){
            cardTitle.textContent = 'Final Exam Study Questions';
            cardContent.textContent = `${allQuestions.length} Questions available. Press Shuffle to start a ${sessionSize}-question review.`;
            nextBtn.disabled = true;
            prevBtn.disabled = true;
            progressText.textContent = '';
            return;
        }
        if(index >= sessionQuestions.length){
            cardTitle.textContent = 'Session complete';
            cardContent.textContent = `You reviewed ${asked} questions.`;
            nextBtn.disabled = true;
            prevBtn.disabled = false; // allow restart
            progressText.textContent = `${asked} / ${sessionSize}`;
            // enable shuffle at end
            shuffleBtn.disabled = false;
            return;
        }
        cardTitle.textContent = `Question ${index+1}`;
        cardContent.textContent = sessionQuestions[index];
        nextBtn.disabled = false;
        prevBtn.disabled = false; // restart available
        progressText.textContent = `${asked} / ${sessionSize}`;
    }

    // load questions from server
    fetch('/api/questions').then(r=>{
        if(!r.ok) return [];
        return r.json();
    }).then(data=>{
        allQuestions = Array.isArray(data) ? data : [];
        renderCard();
    }).catch(err=>{ console.error('Failed to load questions', err); renderCard(); });

    // Shuffle starts a session of sessionSize random questions
    shuffleBtn.addEventListener('click', ()=>{
        if(!allQuestions || allQuestions.length===0) return;
        const pool = [...allQuestions];
        shuffleArray(pool);
        sessionQuestions = pool.slice(0, sessionSize);
        shuffleBtn.disabled = true; // disable until session ends
        index = 0; asked = 0;
        // change prev to be restart
        prevBtn.textContent = 'Restart';
        prevBtn.disabled = false;
        renderCard();
    });

    // Next: save result and advance; stop after sessionSize
    nextBtn.addEventListener('click', async ()=>{
        if(!sessionQuestions || index >= sessionQuestions.length) return;
        // save result (simple stub: score 1 per question)
        const payload = { gameKey: 'review200', player: 'anonymous', score: 1 };
        try{
            await fetch('/api/score', {method:'POST', headers:{'Content-Type':'application/json'}, body: JSON.stringify(payload)});
        }catch(e){ console.warn('Failed to save score', e); }
        asked++;
        index++;
        if(asked >= sessionSize || index >= sessionQuestions.length){
            // end session
            renderCard();
            return;
        }
        renderCard();
    });

    // Prev acts as Restart: reset session state
    prevBtn.addEventListener('click', ()=>{
        // reset everything
        sessionQuestions = [];
        index = 0; asked = 0;
        prevBtn.textContent = 'Prev';
        prevBtn.disabled = true;
        nextBtn.disabled = true;
        shuffleBtn.disabled = false;
        renderCard();
    });

    // About hover collapse (requires bootstrap bundle to be loaded before this script runs)
    const aboutLink = document.querySelector('.nav-link[data-bs-target="#navbarHeader"]');
    const header = document.getElementById('navbarHeader');
    if (aboutLink && header && typeof bootstrap !== 'undefined') {
        const bsHeader = bootstrap.Collapse.getOrCreateInstance(header, { toggle: false });
        let hideTimer = null;
        function show() { clearTimeout(hideTimer); bsHeader.show(); }
        function hide() { clearTimeout(hideTimer); hideTimer = setTimeout(() => bsHeader.hide(), 150); }
        aboutLink.addEventListener('mouseenter', show);
        aboutLink.addEventListener('mouseleave', hide);
        header.addEventListener('mouseenter', () => clearTimeout(hideTimer));
        header.addEventListener('mouseleave', hide);
    }

});

// YouTube playlist and modal player logic (migrated from youtube.html)
document.addEventListener('DOMContentLoaded', function () {
    const videos = [
        { id: 'Rcb6I7gsl-Y', title: 'Video 1 - Intro' },
        { id: 'wNCD3rLbCBk', title: 'Video 2 - Topic' },
        { id: 'NMeuwYvlBI0', title: 'Video 3 - Deep dive' },
        { id: 'xd3re7MY3Gw', title: 'Video 4 - Summary' }
    ];

    const carouselInner = document.getElementById('carouselInner');
    if (carouselInner) {
        videos.forEach((v, i) => {
            const active = i === 0 ? ' active' : '';
            const div = document.createElement('div');
            div.className = 'carousel-item' + active;
            div.innerHTML = `
                                <div class="d-flex justify-content-center p-3">
                                    <button type="button" class="btn btn-link p-0 thumbnail-btn" data-video-id="${v.id}" aria-label="Play ${v.title}">
                                        <img src="https://i.ytimg.com/vi/${v.id}/hqdefault.jpg" alt="${v.title} thumbnail" class="img-fluid" style="max-height:320px;">
                                    </button>
                                </div>
                                <div class="carousel-caption d-none d-md-block"><h5>${v.title}</h5></div>
                            `;
            carouselInner.appendChild(div);
        });
    }

    // Modal and player management
    let ytPlayer = null;
    let currentVideoIndex = 0;
    let captionsOn = false;
    const playerContainer = document.getElementById('playerContainer');
    const videoModalEl = document.getElementById('videoModal');
    const videoModal = videoModalEl ? new bootstrap.Modal(videoModalEl) : null;

    function loadYouTubeAPI(callback) {
        if (window.YT && window.YT.Player) return callback();
        const s = document.createElement('script');
        s.src = 'https://www.youtube.com/iframe_api';
        document.head.appendChild(s);
        window.onYouTubeIframeAPIReady = callback;
    }

    function openPlayerById(id, index) {
        currentVideoIndex = index;
        const opts = `?rel=0&enablejsapi=1&playsinline=1${captionsOn ? '&cc_load_policy=1' : ''}`;
        if (playerContainer) playerContainer.innerHTML = `<div id="yt-player"></div>`;
        loadYouTubeAPI(() => {
            if (ytPlayer) { try { ytPlayer.destroy(); } catch(e) {} ytPlayer = null; }
            ytPlayer = new YT.Player('yt-player', {
                height: '100%',
                width: '100%',
                videoId: id,
                playerVars: { 'rel': 0, 'playsinline': 1, 'cc_load_policy': captionsOn ? 1 : 0 },
                events: {
                    'onReady': (e) => e.target.playVideo(),
                    'onStateChange': (e) => {/* no-op */ }
                }
            });
        });
        buildRelatedList(index);
        if (videoModal) videoModal.show();
    }

    // Wire thumbnail clicks
    document.addEventListener('click', (ev) => {
        const btn = ev.target.closest && ev.target.closest('.thumbnail-btn');
        if (!btn) return;
        const id = btn.getAttribute('data-video-id');
        const index = videos.findIndex(v => v.id === id);
        if (index >= 0) openPlayerById(id, index);
    });

    // Related suggestions
    function buildRelatedList(activeIndex) {
        const list = document.getElementById('relatedList');
        if (!list) return;
        list.innerHTML = '<h6>Up next</h6>';
        const row = document.createElement('div');
        row.className = 'd-flex gap-2 flex-wrap';
        videos.forEach((v, i) => {
            if (i === activeIndex) return;
            const card = document.createElement('button');
            card.className = 'btn btn-light p-1';
            card.innerHTML = `<img src="https://i.ytimg.com/vi/${v.id}/mqdefault.jpg" alt="${v.title}" style="height:72px;"> <div class="small">${v.title}</div>`;
            card.addEventListener('click', () => openPlayerById(v.id, i));
            row.appendChild(card);
        });
        list.appendChild(row);
    }

    // CC toggle
    const ccToggle = document.getElementById('ccToggle');
    if (ccToggle) {
        ccToggle.addEventListener('click', () => {
            captionsOn = !captionsOn;
            ccToggle.classList.toggle('btn-primary', captionsOn);
            if (!ytPlayer) return; // reload current
            const id = videos[currentVideoIndex].id;
            openPlayerById(id, currentVideoIndex);
        });
    }

    // Keyboard controls when modal open
    document.addEventListener('keydown', (e) => {
        const modalEl = document.getElementById('videoModal');
        if (!modalEl || !modalEl.classList.contains('show')) return; // not open
        if (!ytPlayer) return;
        if (e.code === 'Space') {
            e.preventDefault();
            const state = ytPlayer.getPlayerState();
            if (state === YT.PlayerState.PLAYING) ytPlayer.pauseVideo(); else ytPlayer.playVideo();
        } else if (e.code === 'ArrowRight') {
            const next = (currentVideoIndex + 1) % videos.length;
            openPlayerById(videos[next].id, next);
        } else if (e.code === 'ArrowLeft') {
            const prev = (currentVideoIndex - 1 + videos.length) % videos.length;
            openPlayerById(videos[prev].id, prev);
        }
    });

    // Ensure player stops when modal hidden
    const modalEl = document.getElementById('videoModal');
    if (modalEl) {
        modalEl.addEventListener('hidden.bs.modal', () => {
            if (ytPlayer) { try { ytPlayer.stopVideo(); ytPlayer.destroy(); } catch (e) { } ytPlayer = null; }
            if (playerContainer) playerContainer.innerHTML = '';
        });
    }

});
