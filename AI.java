public class AI {

	/*
	* ATTRIBUTES
	*/
	public int difficulty; //Level of difficulty of the AI
	public int transformation; //Index of the array [...][transformation]
	public int line; //Index of the array [line][...]
	public char column; //Index of the colums as a character, has to be turned into an (int)
	public int [] tries;
	public int turn;
	public int test; 
	private int lineInitial;
	public int transformationInitial;
	private int nextLine;
	private int nextColumn;
	
	
	/*
	* CONSTRUCTOR
	*/
	public AI () {
		this.difficulty = 1; //Default difficulty is set to 1
		this.line=0; //Default line index is 0
		this.column = 'A'; //Default column index is A (65 (after transformation: 0))
		this.tries = new int[4];
		this.test = 0;
		this.lineInitial = 0;
		this.transformationInitial = 0;
		this.turn = 1;
		this.nextLine = 0;
		this.nextColumn = 0;
	}
	
	/*
	* METHODS
	*/
	public void whichDifficulty (int a, StrategyGrid grid1, PrivateGrid grid2 ) {
		this.difficulty = a;
		switch (a) {
			case 1:
				computerPlay1(grid1, grid2);
				break;
			case 2: 
				computerPlay2(grid1, grid2);
				break;
		}
	}
	
		/**
		 * DUMB AI
		 */

	/**Computer plays randomly at each turn*/
	private void computerPlay1 (StrategyGrid tacticalgrid, PrivateGrid privateArray ) {
		
		do{
			position(); //target randomly a boat
		} while(tacticalgrid.tacticalGrid[this.line-1][this.transformation]!=0);
		
		tacticalgrid.hit(this.line,this.transformation); // Apply this targeting point (see hit method in StrategyGrid)
		
		/**Updates and displays the new private grid*/
		privateArray.updatePrivate(this.line, this.transformation);
		privateArray.displayPrivate();
	}
	


		/**
			* SMART AI
		*/
	
	/**Computer has a strategic approach when touching one cell of one opponent's ship*/
	private void computerPlay2(StrategyGrid grid, PrivateGrid privateArray) {
		int k = 0;

		switch(this.test){

			case 0 : 
				do{
					position(); //targets random coordinates
				} while(grid.tacticalGrid[this.line-1][this.transformation] != 0);

				this.lineInitial = this.line; 
				this.transformationInitial = this.transformation;
				this.nextLine = this.lineInitial;
				this.nextColumn = this.transformationInitial;

				grid.hit(this.line,this.transformation);
				privateArray.updatePrivate(this.line, this.transformation  );
				privateArray.displayPrivate();
				if(grid.touched){
					k = 1;
				}

			break;

			
			case 1: //try on the bottom (meaning one line below, same column)
				
				try{
					this.nextLine++;

					if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] != 0){
						this.nextLine = this.lineInitial;
						this.nextColumn = this.transformationInitial;

						if(this.turn == 1){
							//Case 2
							try{
								this.nextColumn--;
			
								if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] != 0){
									this.nextLine = this.lineInitial;
									this.nextColumn = this.transformationInitial;
									
									//case 3
									try{
										this.nextLine--;
									
										if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] != 0){
											this.nextLine = this.lineInitial;
											this.nextColumn = this.transformationInitial;
											//case 4
											this.nextColumn++;
									
											grid.hit(this.nextLine,this.nextColumn);
											privateArray.updatePrivate(this.nextLine,this.nextColumn);
											privateArray.displayPrivate();
									
											if(grid.touched){
												this.turn++; //we seek in the same direction
												k = 4;
											}
											if(grid.sunk){
												k = 0;
												this.turn = 1;
											}
										}
										if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] == 0){
											grid.hit(this.nextLine,this.nextColumn);
											privateArray.updatePrivate(this.nextLine, this.nextColumn  );
											privateArray.displayPrivate();
										}

									}catch(Exception e1){
										this.nextLine = this.lineInitial;
										this.nextColumn = this.transformationInitial;
										//case 4
										this.nextColumn++;
									
										grid.hit(this.nextLine,this.nextColumn);
										privateArray.updatePrivate(this.nextLine,this.nextColumn  );
										privateArray.displayPrivate();
									
										if(grid.touched){
											this.turn++; //we seek in the same direction
											k = 4;
										}
										if(grid.sunk){
											k = 0;
											this.turn = 1;
										}
									}	
									if(grid.touched){
										this.turn++; //we seek in the same direction
										k = 3;
									}
									if(!grid.touched && this.turn == 1){
										this.nextLine = this.lineInitial;
										this.nextColumn = this.transformationInitial;
										k = 4; //we try another direction, here to the left
									}
									if(grid.sunk){
										k = 0;
										this.turn = 1;
									}
								}
								
								if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] == 0){
									grid.hit(this.nextLine,this.nextColumn);
									privateArray.updatePrivate(this.nextLine,this.nextColumn  );
									privateArray.displayPrivate();
								}
								
							}catch(Exception e2){
								this.nextLine = this.lineInitial;
								this.nextColumn = this.transformationInitial;
			
								if(this.turn == 1){
									//case 3
									try{
										this.nextLine--;
									
										if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] != 0){
											this.nextLine = this.lineInitial;
											this.nextColumn = this.transformationInitial;
											//case 4
											this.nextColumn++;
									
											grid.hit(this.nextLine,this.nextColumn);
											privateArray.updatePrivate(this.nextLine,this.nextColumn  );
											privateArray.displayPrivate();
									
											if(grid.touched){
												this.turn++; //we seek in the same direction
												k = 4;
											}
											if(grid.sunk){
												k = 0;
												this.turn = 1;
											}
										}
			
										if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] == 0){
											grid.hit(this.nextLine,this.transformationInitial);
											privateArray.updatePrivate(this.nextLine, this.nextColumn );
											privateArray.displayPrivate();
										}
			
									}catch(Exception e3){
										this.nextLine = this.lineInitial;
										this.nextColumn = this.transformationInitial;
										//case 4
										this.nextColumn++;
									
										grid.hit(this.nextLine, this.nextColumn);
										privateArray.updatePrivate(this.nextLine,this.nextColumn  );
										privateArray.displayPrivate();
									
										if(grid.touched){
											this.turn++; //we seek in the same direction
											k = 4;
										}
										if(grid.sunk){
											k = 0;
											this.turn = 1;
										}
									}	
									if(grid.touched){
										this.turn++; //we seek in the same direction
										k = 3;
									}
									if(!grid.touched && this.turn == 1){
										this.nextLine = this.lineInitial;
										this.nextColumn = this.transformationInitial;
										k = 4; //we try another direction, here to the left
									}
									if(grid.sunk){
										k = 0;
										this.turn = 1;
									}
								}
								if(this.turn > 1){
									
									//case 4 
									this.nextColumn++;
							
									grid.hit(this.nextLine,this.nextColumn);
									privateArray.updatePrivate(this.nextLine,this.nextColumn  );
									privateArray.displayPrivate();
							
									if(grid.touched){
										this.turn++; //we seek in the same direction
										k = 4;
									}
									if(grid.sunk){
										k = 0;
										this.turn = 1;
									}
								}
							}

							if(grid.touched){
								this.turn++; //we seek in the same direction
								k = 2;
							}
							if(!grid.touched && this.turn == 1){
								this.nextLine = this.lineInitial;
								this.nextColumn = this.transformationInitial;
								k = 3; //we try another direction, here upwards
							}
							if(!grid.touched && this.turn > 1){
								this.nextLine = this.lineInitial;
								this.nextColumn = this.transformationInitial;
								k = 4; //we go to the opposite direction ( right )
							}
							if(grid.sunk){
								k = 0;
								this.turn = 1;
							}

						}
						if (this.turn >1){
							//Case 3
							this.nextLine--;
							grid.hit(this.nextLine,this.nextColumn);
							privateArray.updatePrivate(this.nextLine, this.nextColumn  );
							privateArray.displayPrivate();
					
							if(grid.touched){
								this.turn++; //we seek in the same direction
								k = 3;
							}

							if(grid.sunk){
								k = 0;
								this.turn = 1;
							}

						}


					}
					if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] == 0){
						grid.hit(this.nextLine,this.nextColumn);
						privateArray.updatePrivate(this.nextLine, this.nextColumn  );
						privateArray.displayPrivate();
					}

				}	catch(Exception e4){
					this.nextLine = this.lineInitial;
					this.nextColumn = this.transformationInitial;
					
					if (this.turn == 1){
						//case 2 
						try{
							this.nextColumn--;
		
							if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] != 0){
								this.nextLine = this.lineInitial;
								this.nextColumn = this.transformationInitial;
								
								if(this.turn==1){
									//case 3
									try{
										this.nextLine--;
									
										if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] != 0){
											this.nextLine = this.lineInitial;
											this.nextColumn = this.transformationInitial;
											//case 4
											this.nextColumn++;
									
											grid.hit(this.nextLine,this.nextColumn);
											privateArray.updatePrivate(this.nextLine,this.nextColumn);
											privateArray.displayPrivate();
									
											if(grid.touched){
												this.turn++; //we seek in the same direction
												k = 4;
											}
											if(grid.sunk){
												k = 0;
												this.turn = 1;
											}
										}
										if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] == 0){
											grid.hit(this.nextLine,this.nextColumn);
											privateArray.updatePrivate(this.nextLine, this.nextColumn  );
											privateArray.displayPrivate();
										}
					
									}catch(Exception e5){
										this.nextLine = this.lineInitial;
										this.nextColumn = this.transformationInitial;
										//case 4
										this.nextColumn++;
									
										grid.hit(this.nextLine,this.nextColumn);
										privateArray.updatePrivate(this.nextLine,this.nextColumn  );
										privateArray.displayPrivate();
									
										if(grid.touched){
											this.turn++; //we seek in the same direction
											k = 4;
										}
										if(grid.sunk){
											k = 0;
											this.turn = 1;
										}
									}	
									if(grid.touched){
										this.turn++; //we seek in the same direction
										k = 3;
									}
									if(!grid.touched && this.turn == 1){
										this.nextLine = this.lineInitial;
										this.nextColumn = this.transformationInitial;
										k = 4; //we try another direction, here to the left
									}
									if(grid.sunk){
										k = 0;
										this.turn = 1;
									}
								}
								if(this.turn>1){
									//case 4 
									this.nextColumn++;
						
									grid.hit(this.nextLine,this.nextColumn);
									privateArray.updatePrivate(this.nextLine,this.nextColumn);
									privateArray.displayPrivate();
									
									if(grid.touched){
										this.turn++; //we seek in the same direction
										k = 4;
									}
									if(grid.sunk){
										k = 0;
										this.turn = 1;
									}
								}
									
								grid.hit(this.nextLine,this.nextColumn);
								privateArray.updatePrivate(this.nextLine,this.nextColumn  );
								privateArray.displayPrivate();
							}
							
						}catch(Exception e6){
							this.nextLine = this.lineInitial;
							this.nextColumn = this.transformationInitial;
		
							if(this.turn == 1){
								//case 3
								try{
									this.nextLine--;
								
									if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] != 0){
										this.nextLine = this.lineInitial;
										this.nextColumn = this.transformationInitial;
										//case 4
										this.nextColumn++;
								
										grid.hit(this.nextLine,this.nextColumn);
										privateArray.updatePrivate(this.nextLine,this.nextColumn  );
										privateArray.displayPrivate();
								
										if(grid.touched){
											this.turn++; //we seek in the same direction
											k = 4;
										}
										if(grid.sunk){
											k = 0;
											this.turn = 1;
										}
									}
		
									if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] == 0){
										grid.hit(this.nextLine,this.transformationInitial);
										privateArray.updatePrivate(this.nextLine, this.nextColumn );
										privateArray.displayPrivate();
									}
		
								}catch(Exception e7){
									this.nextLine = this.lineInitial;
									this.nextColumn = this.transformationInitial;
									//case 4
									this.nextColumn++;
								
									grid.hit(this.nextLine, this.nextColumn);
									privateArray.updatePrivate(this.nextLine,this.nextColumn  );
									privateArray.displayPrivate();
								
									if(grid.touched){
										this.turn++; //we seek in the same direction
										k = 4;
									}
									if(grid.sunk){
										k = 0;
										this.turn = 1;
									}
								}	
								if(grid.touched){
									this.turn++; //we seek in the same direction
									k = 3;
								}
								if(!grid.touched && this.turn == 1){
									this.nextLine = this.lineInitial;
									this.nextColumn = this.transformationInitial;
									k = 4; //we try another direction, here to the left
								}
								if(grid.sunk){
									k = 0;
									this.turn = 1;
								}
							}
							if(this.turn > 1){
								
								//case 4 
								this.nextColumn++;
						
								grid.hit(this.nextLine,this.nextColumn);
								privateArray.updatePrivate(this.nextLine,this.nextColumn  );
								privateArray.displayPrivate();
						
								if(grid.touched){
									this.turn++; //we seek in the same direction
									k = 4;
								}
								if(grid.sunk){
									k = 0;
									this.turn = 1;
								}
							}
						}
						if(grid.touched){
							this.turn++; //we seek in the same direction
							k = 2;
						}
						if(!grid.touched && this.turn == 1){
							this.nextLine = this.lineInitial;
							this.nextColumn = this.transformationInitial;
							k = 3; //we try another direction, here upwards
						}
						if(!grid.touched && this.turn > 1){
							this.nextLine = this.lineInitial;
							this.nextColumn = this.transformationInitial;
							k = 4; //we go to the opposite direction ( right )
						}
						if(grid.sunk){
							k = 0;
							this.turn = 1;
						}
					}
					if (this.turn > 1){
						//case 3				
						try{
							this.nextLine--;
						
							if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] != 0){
								this.nextLine = this.lineInitial;
								this.nextColumn = this.transformationInitial;
								//case 4
								this.nextColumn++;
						
								grid.hit(this.nextLine,this.nextColumn);
								privateArray.updatePrivate(this.nextLine,this.nextColumn  );
								privateArray.displayPrivate();
						
								if(grid.touched){
									this.turn++; //we seek in the same direction
									k = 4;
								}
								if(grid.sunk){
									k = 0;
									this.turn = 1;
								}
							}
							if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] == 0){
								grid.hit(this.nextLine,this.nextColumn);
								privateArray.updatePrivate(this.nextLine, this.nextColumn  );
								privateArray.displayPrivate();
							}
		
						}catch(Exception e8){
							this.nextLine = this.lineInitial;
							this.nextColumn = this.transformationInitial;
							//case 4
							this.nextColumn++;
						
							grid.hit(this.nextLine,this.nextColumn);
							privateArray.updatePrivate(this.nextLine,this.nextColumn  );
							privateArray.displayPrivate();
						
							if(grid.touched){
								this.turn++; //we seek in the same direction
								k = 4;
							}
							if(grid.sunk){
								k = 0;
								this.turn = 1;
							}
						}	
						if(grid.touched){
							this.turn++; //we seek in the same direction
							k = 3;
						}
						if(!grid.touched && this.turn == 1){
							this.nextLine = this.lineInitial;
							this.nextColumn = this.transformationInitial;
							k = 4; //we try another direction, here to the left
						}
						if(grid.sunk){
							k = 0;
							this.turn = 1;
						}
					}
				}
				
				if(grid.touched){
					this.turn++; //we seek in the same direction
					k = 1;
				}
				if(!grid.touched && this.turn == 1){
					this.nextLine = this.lineInitial;
					this.nextColumn = this.transformationInitial;
					k = 2; //we try another direction, here to the left
				}
				if(!grid.touched && this.turn > 1){
					this.nextLine = this.lineInitial;
					this.nextColumn = this.transformationInitial;
					k = 3; //we go to the opposite direction (top)
				}
				if(grid.sunk){
					k = 0;
					this.turn = 1;
				}
				break;
				
			case 2: //try on the left(meaning one column before, same line)
			
				try{
					this.nextColumn--;

					if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] != 0){
						this.nextLine = this.lineInitial;
						this.nextColumn = this.transformationInitial;
						
						if(this.turn==1){
							//case 3
							try{
								this.nextLine--;
							
								if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] != 0){
									this.nextLine = this.lineInitial;
									this.nextColumn = this.transformationInitial;
									//case 4
									this.nextColumn++;
							
									grid.hit(this.nextLine,this.nextColumn);
									privateArray.updatePrivate(this.nextLine,this.nextColumn);
									privateArray.displayPrivate();
							
									if(grid.touched){
										this.turn++; //we seek in the same direction
										k = 4;
									}
									if(grid.sunk){
										k = 0;
										this.turn = 1;
									}
								}
								if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] == 0){
									grid.hit(this.nextLine,this.nextColumn);
									privateArray.updatePrivate(this.nextLine, this.nextColumn  );
									privateArray.displayPrivate();
								}
			
							}catch(Exception e9){
								this.nextLine = this.lineInitial;
								this.nextColumn = this.transformationInitial;
								//case 4
								this.nextColumn++;
							
								grid.hit(this.nextLine,this.nextColumn);
								privateArray.updatePrivate(this.nextLine,this.nextColumn  );
								privateArray.displayPrivate();
							
								if(grid.touched){
									this.turn++; //we seek in the same direction
									k = 4;
								}
								if(grid.sunk){
									k = 0;
									this.turn = 1;
								}
							}	
							if(grid.touched){
								this.turn++; //we seek in the same direction
								k = 3;
							}
							if(!grid.touched && this.turn == 1){
								this.nextLine = this.lineInitial;
								this.nextColumn = this.transformationInitial;
								k = 4; //we try another direction, here to the left
							}
							if(grid.sunk){
								k = 0;
								this.turn = 1;
							}
						}else{
							//case 4 
							this.nextColumn++;
				
							grid.hit(this.nextLine,this.nextColumn);
							privateArray.updatePrivate(this.nextLine,this.nextColumn);
							privateArray.displayPrivate();
							
							if(grid.touched){
								this.turn++; //we seek in the same direction
								k = 4;
							}
							if(grid.sunk){
								k = 0;
								this.turn = 1;
							}
						}
							
						grid.hit(this.nextLine,this.nextColumn);
						privateArray.updatePrivate(this.nextLine,this.nextColumn  );
						privateArray.displayPrivate();
					}
					
				}catch(Exception e10){
					this.nextLine = this.lineInitial;
					this.nextColumn = this.transformationInitial;

					if(this.turn == 1){
						//case 3
						try{
							this.nextLine--;
						
							if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] != 0){
								this.nextLine = this.lineInitial;
								this.nextColumn = this.transformationInitial;
								//case 4
								this.nextColumn++;
						
								grid.hit(this.nextLine,this.nextColumn);
								privateArray.updatePrivate(this.nextLine,this.nextColumn  );
								privateArray.displayPrivate();
						
								if(grid.touched){
									this.turn++; //we seek in the same direction
									k = 4;
								}
								if(grid.sunk){
									k = 0;
									this.turn = 1;
								}
							}

							if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] == 0){
								grid.hit(this.nextLine,this.transformationInitial);
								privateArray.updatePrivate(this.nextLine, this.nextColumn );
								privateArray.displayPrivate();
							}

						}catch(Exception e11){
							this.nextLine = this.lineInitial;
							this.nextColumn = this.transformationInitial;
							//case 4
							this.nextColumn++;
						
							grid.hit(this.nextLine, this.nextColumn);
							privateArray.updatePrivate(this.nextLine,this.nextColumn  );
							privateArray.displayPrivate();
						
							if(grid.touched){
								this.turn++; //we seek in the same direction
								k = 4;
							}
							if(grid.sunk){
								k = 0;
								this.turn = 1;
							}
						}	
						if(grid.touched){
							this.turn++; //we seek in the same direction
							k = 3;
						}
						if(!grid.touched && this.turn == 1){
							this.nextLine = this.lineInitial;
							this.nextColumn = this.transformationInitial;
							k = 4; //we try another direction, here to the left
						}
						if(grid.sunk){
							k = 0;
							this.turn = 1;
						}
					}
					if(this.turn > 1){
						
						//case 4 
						this.nextColumn++;
				
						grid.hit(this.nextLine,this.nextColumn);
						privateArray.updatePrivate(this.nextLine,this.nextColumn  );
						privateArray.displayPrivate();
				
						if(grid.touched){
							this.turn++; //we seek in the same direction
							k = 4;
						}
						if(grid.sunk){
							k = 0;
							this.turn = 1;
						}
					}
				}
				if(grid.touched){
					this.turn++; //we seek in the same direction
					k = 2;
				}
				if(!grid.touched && this.turn == 1){
					this.nextLine = this.lineInitial;
					this.nextColumn = this.transformationInitial;
					k = 3; //we try another direction, here upwards
				}
				if(!grid.touched && this.turn > 1){
					this.nextLine = this.lineInitial;
					this.nextColumn = this.transformationInitial;
					k = 4; //we go to the opposite direction ( right )
				}
				if(grid.sunk){
					k = 0;
					this.turn = 1;
				}
			break;

			case 3: //try on the top (meaning one line above, same column)
		
				try{
					this.nextLine--;
				
					if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] != 0){
						this.nextLine = this.lineInitial;
						this.nextColumn = this.transformationInitial;
						//case 4
						this.nextColumn++;
				
						grid.hit(this.nextLine,this.nextColumn);
						privateArray.updatePrivate(this.nextLine,this.nextColumn);
						privateArray.displayPrivate();
				
						if(grid.touched){
							this.turn++; //we seek in the same direction
							k = 4;
						}
						if(grid.sunk){
							k = 0;
							this.turn = 1;
						}
					}
					if(grid.tacticalGrid[this.nextLine-1][this.nextColumn] == 0){
						grid.hit(this.nextLine,this.nextColumn);
						privateArray.updatePrivate(this.nextLine, this.nextColumn );
						privateArray.displayPrivate();
					}

				}catch(Exception e12){
					this.nextLine = this.lineInitial;
					this.nextColumn = this.transformationInitial;
					//case 4
					this.nextColumn++;
				
					grid.hit(this.nextLine,this.nextColumn);
					privateArray.updatePrivate(this.nextLine,this.nextColumn);
					privateArray.displayPrivate();
				
					if(grid.touched){
						this.turn++; //we seek in the same direction
						k = 4;
					}
					if(grid.sunk){
						k = 0;
						this.turn = 1;
					}
				}

				if(grid.touched){
					this.turn++; //we seek in the same direction
					k = 3;
				}
				if(!grid.touched && this.turn == 1){
					this.nextLine = this.lineInitial;
					this.nextColumn = this.transformationInitial;
					k = 4; //we try another direction, here to the left
				}
				if(grid.sunk){
					k = 0;
					this.turn = 1;
				}
			break;

			case 4: //try on the right (meaning one column after, same line)
			
				this.nextColumn++;
				
				grid.hit(this.nextLine,this.nextColumn);
				privateArray.updatePrivate(this.nextLine,this.nextColumn);
				privateArray.displayPrivate();
				
				if(grid.touched){
					this.turn++; //we seek in the same direction
					k = 4;
				}
				if(grid.sunk){
					k = 0;
					this.turn = 1;
				}
			break;	
		}
		this.test = k;	
	}


	/**Method to have a random position on the grid*/
	private void position () {
		/**Randomly choose a line and a column the computer will target*/
		this.line = (int)(Math.random()*10+1); //Gives a line between 1 and 10
		this.column = (char)((Math.random()*10)+65); //Gives a column between (65..74), in ASCII A..J
		
		/**Convert the (char) column into an int*/
		this.transformation = this.column;
		this.transformation = this.transformation - 65; //Maps the ASCII format (65..74) to the array indexes (0..9)
	}
}
