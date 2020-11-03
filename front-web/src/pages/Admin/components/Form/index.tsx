import { makeRequest } from 'core/utils/request';
import React, { useState } from 'react'
import BaseForm from '../BaseForm';
import './styles.scss'

type FormState = {
    name: string;
    price: string;
    category: string;
    description: string;
}

type FormEvent = React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement | HTMLSelectElement>

const Form = () => {
    const [formdata, setFormdata] = useState<FormState>({ name: '', price: '', category: '1', description: '' });

    const handleOnChange = (event: FormEvent) => {
        const name = event.target.name;
        const value = event.target.value;

        setFormdata(data => ({ ...data, [name]: value }));
    }

    const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
        event.preventDefault();
        const payLoad = {
            ...formdata,
            imgUrl: 'https://imagens.canaltech.com.br/ofertas/o14327.1.jpg',
            categories: [{ id: formdata.category }]
        }

        makeRequest({ url: '/products', method: 'POST', data: payLoad });
        console.log(payLoad)
    }

    return (
        <form onSubmit={handleSubmit}>
            <BaseForm title="CADASTRAR UM PRODUTO">
                <div className="row">
                    <div className="col-6">
                        <input
                            value={formdata.name}
                            name="name"
                            type="text"
                            className="form-control"
                            onChange={handleOnChange}
                            placeholder="Nome do Produto"
                        />
                        <select
                            value={formdata.category}
                            name="category"
                            className="form-control mb-5"
                            onChange={handleOnChange}
                        >
                            <option value="1">Livros</option>
                            <option value="2">Eletronicos</option>
                            <option value="3">Computador</option>
                        </select>
                        <input
                            value={formdata.price}
                            name="price"
                            type="text"
                            className="form-control"
                            onChange={handleOnChange}
                            placeholder="Preço"
                        />
                    </div>
                    <div className="col-6">
                        <textarea
                            className="form-control"
                            name="description"
                            value={formdata.description}
                            cols={30}
                            rows={10}
                            onChange={handleOnChange}
                        />
                    </div>
                </div>
            </BaseForm>
        </form>
    );
};

export default Form;