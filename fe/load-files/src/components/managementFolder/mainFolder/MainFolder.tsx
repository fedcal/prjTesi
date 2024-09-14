import { useState} from 'react'
import './mainFolder.css'

function MainFolder() {
  return (
    <>
      <div className="spiegazione-folder">
      <h1 className='title'>Gestione cartelle</h1>
        <p className='spiegazione'>
          In questa sezione si possono visualizzare, creare, modificare ed eliminare le cartelle che contengono i file sui quali i vari bot vengono addestrati.
        </p>
      </div>
    </>
  )
}

export default MainFolder
