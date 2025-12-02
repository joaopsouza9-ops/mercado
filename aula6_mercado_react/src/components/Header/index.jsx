import { Link } from 'react-router-dom';
import logo from '../../assets/logo.png'
import './style.css'

function Header(){
    return(
        <header className='header'>
            <div className="header__wrap">
                <Link to="/" className='brand'>
                    <img src={logo} alt="logo do mercado" />
                    <span>Mercado qualquer</span>
                </Link>
                <nav className='nav'>
                    <Link to="/cadastro" className='navLink'>Adicionar produtos</Link>
                    <Link to="/carrinho" className='navLink'>Carrinho</Link>
                    <Link to="/comprados" className='navLink'>Comprados</Link>
                    <Link to="/sobre-nos" className='navLink'>sobre_nós</Link>
                </nav>
            </div>
        </header>
    );
}

export default Header;