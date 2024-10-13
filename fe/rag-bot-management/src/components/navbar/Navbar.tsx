import React from 'react'
import { AppBar, Toolbar, Typography, Button } from '@mui/material';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import Guida from '../../pages/Guida';
import ChatBot from '../../pages/ChatBot';
import BotRagManagement from '../../pages/BotRagManagement';
import './navbar.css';

function Navbar() {
  return (
    <>
       <AppBar position="static" sx={{ backgroundColor: 'rgb(191, 226, 253)' }}>
            <Toolbar>
              <Typography variant="h6" component="div" sx={{ flexGrow: 1 }}>
                <Button color="inherit" component={Link} to="/" className="navbar-button">
                  ChatBot Rag
                </Button>
              </Typography>
              <Button color="inherit" component={Link} to="/">
                Guida
              </Button>
              <Button color="inherit" component={Link} to="/chat">
                Chat
              </Button>
              <Button color="inherit" component={Link} to="/gestione-bot">
                Gestione Bot
              </Button>
            </Toolbar>
          </AppBar> 
    </>
  )
}

export default Navbar