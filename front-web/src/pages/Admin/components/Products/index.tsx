import React from 'react'
import { Route, Switch } from 'react-router-dom';
import Form from '../Form';
import List from './List';
import './style.scss'

const Products = () => {


    return (
        <div className="products-container">
            <Switch>
                <Route path="/admin/products" exact>
                    <List />
                </Route>
                <Route path="/admin/products/create">
                    <Form/>
                </Route>
                <Route path="/admin/products/:productId">
                    <h1>Mostrar um produto</h1>
                </Route>
            </Switch>

        </div>
    )
}

export default Products;