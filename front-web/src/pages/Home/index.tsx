import React from 'react'
import './styles.scss'
import { ReactComponent as MainImage } from '../../core/assets/images/mainImage.svg'
import ButtonIcon from '../../core/components/ButtonIcon';
import { Link } from 'react-router-dom'


const Home = () => (
    <div className=" home-container">
        <div className="row card-base border-radius-20 home-content">
            <div className="col-6">
                <h1 className="text-title">Conheça o melhor<br />catálogo de produtos</h1>
                <p className="text-subtitle">Ajudaremos você a encontrar os melhores <br />produtos disponíveis no mercado.</p>
                <Link to="/products"><ButtonIcon text="INICIE AGORA A SUA BUSCA" /></Link>
            </div>
            <div className="col-6">
                <MainImage className="main-image" />
            </div>

        </div>
    </div>
);

export default Home;