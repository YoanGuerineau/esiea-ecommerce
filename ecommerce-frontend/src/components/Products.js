import './products.css';

export default function Products(props) {

	const getProductClassName = (product) => {
		return product.cost === 500 ? "product product--500" : "product";
	}
	const getDeleteButtonClassName = (product) => {
		return product.cost === 500 ? "product-delete-button product-delete-button--500" : "product-delete-button";
	}

	const products = props.data.map( (product) => {
		return (
			<div key={product.id} className={getProductClassName(product)}>
				<img src={props.validateUrl(props.url) ? props.url : ""} alt="Aperçu du produit"/>
				<h3>Product: {product.name}</h3>
				<p>Description: {product.description}</p>
				<p>Cost: {product.cost} €</p>

				<button 
					className={getDeleteButtonClassName(product)}
					onClick={ (event) => props.handleDelete(event, product.id)}
				>Delete</button>
			</div>
		);
	});

	return (
		<div className="products">
			<h2 className="products-title">Available products</h2>
			<div className="products-container">
				{products}
			</div>
		</div>
	);
}
