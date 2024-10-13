import React from 'react';
import './App.css';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Navbar from './components/navbar/Navbar';

import Guida from './pages/Guida';
import ChatBot from './pages/ChatBot';
import BotRagManagement from './pages/BotRagManagement';

function App() {
  return (
    <>
      <Router>
        <div>
        
          <Navbar/>

          <Routes>
            <Route path="/" element={<Guida />} />
            <Route path="/chat" element={<ChatBot />} />
            <Route path="/gestione-bot" element={<BotRagManagement />} />
          </Routes>
        </div>
      </Router>
    </>
  );
}

export default App;
