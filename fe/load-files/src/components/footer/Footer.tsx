import React from 'react'
import UnibaLogo from '../../assets/uniba-logo.png';
import LinksLogo from '../../assets/links-logo.png';
import './footer.css'

function Footer() {
  return (
    <div>
      <footer>
        <div className="footer-content">
            <h3>Progetto tesi di laurea</h3>
            <p>Realizzato da Federico Calò, matricola 678191</p>
            <ul className='links'>
              <li><a href="https://www.uniba.it/it" target='_blank' rel="noopener, noreferrer"><img src={UnibaLogo} alt="Logo universitààà di Bari"/></a></li>
              <li><a href="https://www.linksmt.it/web/guest" target='_blank' rel="noopener, noreferrer"><img src={LinksLogo} className='logo-links' alt="Logo Links Management and System"/></a></li>
            </ul>
        </div>

      </footer>
    </div>
  )
}

export default Footer
