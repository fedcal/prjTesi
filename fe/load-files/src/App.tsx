import { Route, Routes } from 'react-router-dom';
import Home from './pages/Home';
import ManagementDocuments from './pages/ManagementDocuments';
import ManagementFolders from './pages/ManagementFolders';

function App() {
  return (
    <>
      <Routes>
        <Route path="/" element={<Home/>}/>
        <Route path="/gestione-documenti" element={<ManagementDocuments/>}/>
        <Route path="/gestione-cartelle" element={<ManagementFolders/>}/>
      </Routes>
    </>
  );
}

export default App;
