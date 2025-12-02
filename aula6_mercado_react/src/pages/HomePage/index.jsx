import { useEffect, useState } from 'react';
import './style.css';
import logo from '../../assets/logo.png'
import api from '../../services/api';
import { toast } from 'react-toastify';

<div className="home">
        {/* logo / Landing */}
        <section className="logo">
            <div className="logo__text">
                <h1>Mercado
                    Qualquer</h1>
                <p>Tudo o que você precisa em um só lugar!</p>
            </div>
            <div className="logo__image">
                <img src={logo} alt="logo do mercado" />
            </div>
        </section>
</div>

function HomePage() {
    

    const [produtos, setProdutos] = useState([]);
    const [carregando, setCarregando] = useState(true);

    useEffect(() => {
        async function buscarProdutos() {
            try {
                const response = await api.get('/cadastro');
                setProdutos(response.data);
                setCarregando(false);
            } catch (error) {
                console.error('Erro ao buscar produtos:', error);
                const mensagemError = error?.response?.data?.mensagem || 'Erro ao buscar produtos';
                toast.error(mensagemError);
                setCarregando(false)
            }
        }
        buscarProdutos();
    }, []);

    if (carregando) {
        return <div><h2>Carregando...</h2></div>;
    }

    return (
        <div className="lista-produtos-container">
            <h1>Produtos disponiveis</h1>
            {produtos.length === 0 ? (
                <p>Nenhum produto encontrado.</p>
            ) : (
                <table className='tabelaProduto'>
                    <thead>
                        <tr>
                            <th>Nome</th>
                            <th>R$</th>
                        </tr>
                    </thead>
                    <tbody>
                        {produtos.map(produto => (
                            <tr key={produto.nome}>
                                <td>{produto.nome}</td>
                                <td>{produto.preco}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default HomePage;