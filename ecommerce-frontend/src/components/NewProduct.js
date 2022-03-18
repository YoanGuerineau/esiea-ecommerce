import './newProduct.css';

export default function NewProduct(props) {
	return (
		<div className="new-product-form">
			<h3 className="new-product-title">Add a product</h3>

			<div className="new-product-container">
				<label>Product name:</label>
				<input 
					type="text" 
					className="new-product-input"
					placeholder="Name goes here"
					name="name"
					value={props.newProduct.name}
					onChange={props.handleModification}
				/>
			</div>

			<div className="new-product-container">
				<label>Product cost:</label>
				<input 
					type="number" 
					className="new-product-input"
					placeholder="Cost goes here"
					name="cost"
					value={props.newProduct.cost}
					onChange={props.handleModification}
				/>
			</div>

			<textarea 
				className="new-product-description"
				placeholder="Description goes here"
				name="description"
				value={props.newProduct.description}
				onChange={props.handleModification}
			/>

			{props.invalidInput ? <p>{props.invalidInput}</p> : ""}

			<button 
				className="new-product-submit-button"
				onClick={props.handleSubmit}
			>Add</button>
		</div>
	);
}