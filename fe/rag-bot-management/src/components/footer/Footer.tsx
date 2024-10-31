import React from 'react'
import "./Footer.css"
import { Box, Typography, Container, Grid, Link } from '@mui/material';

function Footer() {
  return (
    <div>
        <Box className= "background-footer">
            <Container maxWidth="lg">
                <Grid container spacing={4} justifyContent="center">
                <Grid item xs={12} sm={4}>
                    <Typography variant="h6" className= "footer-column-title"><Link href="https://www.federicocalo.dev" color="inherit" underline="hover">Federico Calò</Link></Typography>
                    <Typography variant="body2">
                        Studente universitario (matricola 678191) e sviluppatore software.
                    </Typography>
                </Grid>
                <Grid item xs={12} sm={4}  justifyContent="footer-grid-content" >
                    <Typography variant="h6" className= "footer-column-title">Link utili</Typography>
                    <div>
                        <Link href="/" color="inherit" underline="hover">Home</Link>
                        <br />
                        <Link href="/about" color="inherit" underline="hover">Chat</Link>
                        <br />
                        <Link href="/about" color="inherit" underline="hover">Gestione Bot</Link>
                    </div>
                    
                </Grid>
                <Grid item xs={12} sm={4}>
                    <Typography variant="h6" className= "footer-column-title">Contatti</Typography>
                    <Typography variant="body2">Email universitaria: f.calo29@studenti.uniba.it</Typography>
                    <Typography variant="body2">Email personale: fedcal01@gmail.com</Typography>
                    <Typography variant="body2">Cellulare: +39 3332673965</Typography>
                </Grid>
                </Grid>
                <Box textAlign="center" pt={{ xs: 5, sm: 10 }} pb={{ xs: 5, sm: 0 }}>
                <Typography variant="body2">© {new Date().getFullYear()} <Link href="https://www.federicocalo.dev" color="inherit" underline="hover">Federico Calò</Link></Typography>
                </Box>
            </Container>
        </Box>
    </div>
  )
}

export default Footer