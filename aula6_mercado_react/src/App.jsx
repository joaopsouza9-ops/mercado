import {Route, BrowserRouter as Router, Routes } from 'react-router-dom';
import './App.css'
import 'react-toastify/dist/ReactToastify.css'; // Importando estilos do react Toastify

import Footer from './components/Footer';
import { ToastContainer } from 'react-toastify';
import Header from './components/Header';
import CadastroPage from './pages/CadastroPage';
import HomePage from './pages/HomePage';
import SobreNosPage from './pages/SobreNosPage';


function App() {
  return (
    <Router>
      <Header/>
      <main>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/sobre-nos" element={<SobreNosPage />} />
          <Route path='/cadastro' element={<CadastroPage />} />
        </Routes>
      </main>
      <Footer/>
      <ToastContainer position='top-right' autoClose={3000} />
    </Router>
  );
}

export default App
