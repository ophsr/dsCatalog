import React from 'react';
import ButtonIcon from 'core/components/ButtonIcon';
import { Link } from 'react-router-dom';
import AuthCard from '../Card';
import { useForm } from 'react-hook-form';

import './styles.scss';
import { makeLogin } from 'core/utils/request';

type FormData = {
    username: string;
    password: string;
}

const Login = () => {
    const { register, handleSubmit } = useForm<FormData>(); // initialize the hook

    const onSubmit = (data: FormData) => {
        console.log(data);
        makeLogin(data)
    };

    return (
        <AuthCard title="login">
            <form className="login-form" onSubmit={handleSubmit(onSubmit)}>
                <input
                    type="email"
                    className="form-control input-base margin-botton-30"
                    placeholder="Email"
                    ref={register}
                    name="username"
                />

                <input
                    type="password"
                    className="form-control input-base"
                    placeholder="Senha"
                    name="password"
                    ref={register}
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