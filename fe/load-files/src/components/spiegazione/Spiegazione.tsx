import React from 'react'
import Documenti from '../../assets/documenti.jpeg';
import { Link } from 'react-router-dom';
import './spiegazione.css'

function Spiegazione() {
  return (
    <>
      <div className="section">
        <div className="container">
            <div className="content-section">
                <div className="title">
                    <h1>Gestione cartelle e documenti</h1>
                </div>
                <div className="content">
                    <p>
                        Ogni microservizio rappresenta un bot che viene addestrato su determinati file pdf. Per specializzare l'addestramento di ogni bot,
                        si è pensato di creare quuesta sezione in cui gestire le cartelle e i file contenuti in esse. I documenti e le relative cartelle sono tracciate nel database.
                        <br/><br/>
                        In questo modo si possono creare chatbot altamente specializzati nell'ambito che si desidera.
                    </p>
                    <div className="button">
                        <Link to="/gestione-cartelle">Gestione Cartelle</Link>
                        <Link to="/gestione-documenti">Gestione Documenti</Link>
                    </div>
                </div>
            </div>
            <div className="image-section">
                <img src={Documenti} alt="Spiegazione sezione file management" />
            </div>
        </div>
      </div>
    </>
  )
}

export default Spiegazione
