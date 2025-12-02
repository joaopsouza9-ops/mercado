import { useForm } from 'react-hook-form'
import { yupResolver } from '@hookform/resolvers/yup';
import * as yup from 'yup';
import api from '../../services/api';
import { toast } from 'react-toastify';
import './style.css';

const esquemaDeCadastro = yup.object({
    nome: yup.string().required('O nome é obrigatorio'),
    preco: yup.string().required('O preço é obrigatorio')
});

function CadastroPage() {
    // Hook useForm para gerenciar o formulario de cadastro
    const {
        register: registrarCampo,
        handleSubmit: lidarComEnviosDoFormulario,
        formState: { errors: errosDoFormulario, isSubmitting: estaEnviando },
        setError: definirErroNoCampo,
        reset: limparCampoDoFormulario,
    } = useForm({
        resolver: yupResolver(esquemaDeCadastro),
        defaultValues: {
            nome: '',
            preco: '',
        },
    });

    async function enviarDados(dadosDoFormulario) {
        const dadosParaApi = {
            nome: dadosDoFormulario.nome,
            preco: dadosDoFormulario.preco
        };

        try {
            const resposta = await api.post('/cadastro', dadosParaApi);
            toast.success(resposta.data.mensagem || 'Cadastro realizado com sucesso!');
            limparCampoDoFormulario();
        } catch (error) {
            const codigoDeStatus = error?.response?.status;
            const mensagemDoServidor = error?.response?.data?.mensagem || 'Erro ao cadastrar Produto';
            if (codigoDeStatus === 409) {
                definirErroNoCampo('nome', {
                    type: 'server',
                    message: mensagemDoServidor,
                });
            }
            toast.error(mensagemDoServidor);
            console.error('Erro ao cadastra o produto:', error);
        }
    }

    return (
        <div className="cadastro-container">
            <h1>Adicionar novos produtos</h1>
            <form noValidate onSubmit={lidarComEnviosDoFormulario(enviarDados)}>
                <div className="form-group">
                    <label htmlFor="campo-nome">Nome:</label>
                    <input
                        id='campo-nome'
                        type="text"
                        placeholder='Ex.: café Pilão'
                        {...registrarCampo('nome')}
                    />
                </div>
                {errosDoFormulario.nome && (
                    <p className='error-message'>{errosDoFormulario.nome.message}</p>
                )}
                <div className="form-group">
                    <label htmlFor="campo-preco">Preco:</label>
                    <input
                        id='campo-preco'
                        type="text"
                        placeholder='Ex.: 18.99'
                        {...registrarCampo('preco')}
                    />
                </div>
                {errosDoFormulario.preco && (
                    <p className='error-message'>{errosDoFormulario.preco.message}</p>
                )}
                
                <button type='submit' disabled={estaEnviando}>
                    {estaEnviando ? 'Cadastrando...' : 'Cadastrar'}
                </button>
            </form>
        </div>
    );
}

export default CadastroPage;