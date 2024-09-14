import React from 'react'
import Footer from '../components/footer/Footer'
import Navabar from '../components/navbar/Navabar'
import MainFolder from '../components/managementFolder/mainFolder/MainFolder'
import ListaFolderComponent from '../components/managementFolder/listaFolder/ListaFolder'

function ManagementFolders() {
  return (
    <div>
        <Navabar/>
        <MainFolder/>
        <ListaFolderComponent/>
        <Footer/>
    </div>
  )
}

export default ManagementFolders
