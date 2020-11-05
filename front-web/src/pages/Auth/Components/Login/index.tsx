import React, { useState } from 'react';
import ButtonIcon from 'core/components/ButtonIcon';
import { Link, useHistory } from 'react-router-dom';
import AuthCard from '../Card';
import { useForm } from 'react-hook-form';

import './styles.scss';
import { makeLogin } from 'core/utils/request';
import { saveSessionData } from 'core/utils/auth';

type FormData = {
    username: string;
    password: string;
}

const Login = () => {
    const { register, handleSubmit } = useForm<FormData>(); // initialize the hook
    const [ hasError, setHasError ] = useState(false);
    const history = useHistory();
    const onSubmit = (data: FormData) => {
        makeLogin(data) 
            .then(response => {
                setHasError(false)
                saveSessionData(response.data)
                history.push('/admin')
            })
            .catch(()=>{
                setHasError(true)
            })
    };


    return (
        <AuthCard title="login">

            {hasError && (<div className="alert alert-danger mt-5">Usuário ou senha inválidos</div>)}

            <form className="login-form" onSubmit={handleSubmit(onSubmit)}>
                <input
                    type="email"
                    className="form-control input-base margin-botton-30"
                    placeholder="Email"
                    ref={register ({required:true,})}
                    name="username"
                />

                <input
                    type="password"
                    className="form-control input-base"
                    placeholder="Senha"
                    name="password"
                    ref={register ({required:true})}
                />

                <Link to="/admin/auth/recover" className="link-recover">Esqueci a senha?</Link>
                <div className="login-submit">
                    <ButtonIcon text="Logar" />
                </div>
                <div className="text-center">
                    <span className="not-registered">Não tem Cadastro?</span>
                    <Link to="/admin/auth/register" className="link-register">CADASTRAR</Link>
                </div>
            </form>
        </AuthCard>
    )
}
export default Login;