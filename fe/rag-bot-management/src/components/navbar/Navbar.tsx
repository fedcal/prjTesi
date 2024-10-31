import React from 'react'
import { AppBar, Toolbar, Typography, Button } from '@mui/material';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Guida from '../../pages/Guida';
import ChatBot from '../../pages/ChatBot';
import BotRagManagement from '../../pages/BotRagManagement';
import './navbar.css';
import SmartToyIcon from '@mui/icons-material/SmartToy';
import HomeIcon from '@mui/icons-material/Home';
import ChatIcon from '@mui/icons-material/Chat';
import SettingsIcon from '@mui/icons-material/Settings';

function Navbar() {
  return (
    <>
       <AppBar position="static" sx={{ backgroundColor: 'rgb(191, 226, 253)' }}>
            <Toolbar>
              <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                <Button color="inherit" component={Link} to="/" className="logo-navbar">
                  <SmartToyIcon style ={{marginRight: 5}}/> ChatBot Rag
                </Button>
              </Typography>
              <Button color="inherit" component={Link} to="/" className="navbar-button">
                <HomeIcon style ={{marginRight: 5}}/> Home
              </Button>
              <Button color="inherit" component={Link} to="/chat" className="navbar-button">
                <ChatIcon style ={{marginRight: 5}}/> Chat
              </Button>
              <Button color="inherit" component={Link} to="/gestione-bot" className="navbar-button">
                <SettingsIcon style ={{marginRight: 5}}/> Gestione Bot
              </Button>
            </Toolbar>
          </AppBar> 
    </>
  )
}

export default Navbar