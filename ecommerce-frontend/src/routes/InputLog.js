import { Formik, Form, useField, ErrorMessage } from 'formik';
import { useNavigate } from 'react-router-dom';
import * as yup from 'yup';
import { useState } from 'react';

import "../components/newProduct.css"

export default function InputLog() {
	
	const navigate = useNavigate();

	const [errorLogin, setErrorLogin] = useState(false);

	const inputSchema = yup.object(
		{
			username: yup
				.string()
				.matches(/[a-zA-Z0-9]+$/, 'Must not include symbols')
				.max(256, 'Username must be 256 characters long at most')
				.required('username required'),
			password: yup
				.string()
				.matches(/[a-zA-Z0-9]+$/, 'Must not include symbols')
				.max(256, 'Password must be 256 characters long at most')
				.required('password required')
		}
	)
	
	const MyField = (props) => {
		const [field, meta] = useField(props);
		return (
			<div>
				<input
					className={`new-product-input ${meta.touched && meta.error && 'new-product-submit-button-red'}`}
					{...props}
					{...field}
				/>
				<br/>
				<ErrorMessage name={field.name}/>
			</div>
		);
	}


	return (
		<div>
			<h1>Log in</h1>
			<Formik
				initialValues={
					{
						username: '',
						password: ''
					}	
				}
				validationSchema={inputSchema}
				onSubmit={
					values => {
						fetch('http://localhost:9000/api/public/login', 
							{
								method: 'POST',
								headers: {
									'Accept': 'application/json',
									'Content-Type': 'application/json'
								},
								body: JSON.stringify(values)
							}
						).then( (res) => {
							if ( res.status === 200 ) {
								res.body.getReader().read().then( (bodyRes) => {
									let token = "";
									for (let i =0; i< bodyRes.value.length; i++) {
										token += '%' + ('0' + bodyRes.value[i].toString(16)).slice(-2);
									}
									token = decodeURIComponent(token);
									sessionStorage.setItem('token', token);
								}).then( () => {
									navigate("/home");
								})
							} else {
								setErrorLogin({visible: true, message: 'Username or password incorrect!'});
								sessionStorage.removeItem('token');
							}
						}).catch( () => {
							console.log('Error when sending login request');
						});
					}
				}
			>
				<Form>
					<label htmlFor='username' >Username</label>
					<MyField 
						type='username'
						id='username'
						name='username'
						placeholder='My username is...'
					/>

					<label htmlFor='password' >Password</label>
					<MyField 
						type='password'
						id='password'
						name='password'
						placeholder='******'
					/>

					{ errorLogin.visible && <div><small>{errorLogin.message}</small></div>}

					<br/>

					<button type='submit' className='new-product-submit-button'>Log in</button>
				</Form>
			</Formik>
		</div>
	);
}