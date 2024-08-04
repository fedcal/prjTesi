import React from 'react'
import './navbar.css'
import {Link} from 'react-router-dom'

function Navabar() {
  return (
    <>
      <nav>
        <Link to="/" className='title'>File Management</Link>
        <ul>
          <li><Link to="/">Home</Link></li>
          <li><Link to="/gestione-cartelle">Gestione Cartelle</Link></li>
          <li><Link to="/gestione-documenti">Gestione Documenti</Link></li>
        </ul>
      </nav>
    </>
  )
}

export default Navabar
