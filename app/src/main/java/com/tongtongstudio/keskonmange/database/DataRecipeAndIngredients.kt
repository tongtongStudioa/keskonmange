package com.tongtongstudio.keskonmange.database


import android.content.Context
import com.tongtongstudio.keskonmange.R
import com.tongtongstudio.keskonmange.R.string

const val PLAT: String = "Plat"
const val DESSERT: String = "Dessert"
const val ENTREE: String = "Entrée"

class DataRecipeAndIngredients(val context: Context) {
    val listOfIngredients = ArrayList<Ingredients>()
    val listOfRecipes = ArrayList<Recipe>()

    init {
        createListOfNewIngredients()
        createListOfNewRecipes()
    }


    private fun createListOfNewRecipes() {
        // TODO: 23/01/2022 define p, t , c, for quantities


        createAndAddRecipe(
            "Nouilles chinoises aux légumes et aux épices",
            R.drawable.test_recipe,
            "$${getS(string.poireau)}/1;" +
                    "${getS(string.carotte)}/1;" +
                    "${getS(string.courgette)}/2;" +
                    "${getS(string.champignons_de_Paris)}/250g;" +
                    "${getS(string.oignon)}/1;" +
                    "${getS(string.red_bell_pepper)}/1;" +
                    "${getS(string.noodles)}/1p;" + // portion, paquet
                    "${getS(string.soya_sauce)}/3c;",
            25,
            "ÉTAPE 1\n" +
                    "Détailler tous les légumes en fine julienne./" +
                    "ÉTAPE 2\n" +
                    "Faire bouillir une grande casserole d'eau salée, y jeter les nouilles, dès que l'ébullition à repris, couvrir, éteindre le feu et laisser reposer pendant 4 min./" +
                    "ÉTAPE 3\n" +
                    "Pendant ce temps, faire revenir les légumes dans l'huile pendant 5 mn, à feu très vif, ajouter les cinq épices et la sauce de soja./" +
                    "ÉTAPE 4\n" +
                    "Égoutter les nouilles, les joindre aux légumes et rectifier l'assaisonnement si besoin.",
            "C'est un plat parfait pour les végétariens" +
                    " mais peut également être servi avec des escaloppes de poulet ou des filets de poisson.",
            4, PLAT
        )

        createAndAddRecipe(
            "Croque Monsieur etudiant",
            R.drawable.croque_monsieur,
            "Pain de mie/4t;" +
                    "${getS(string.ham)}/2t;" +
                    "${getS(string.butter)};" +
                    getS(string.emmental),
            15,
            "Beurrer les tranches de pain de mie des deux côtés.\n" + // TODO: 23/01/2022 make separation step ';'
                    "\n" +
                    "Couper les tranches de jambon et répartir sur 4 tranches de pain.\n" +
                    "\n" +
                    "Parsemer de gruyère râpé.\n" +
                    "\n" +
                    "Mettre les 2 autres tranches de pain sur les premières.\n" +
                    "\n" +
                    "Faire dorer les croque-monsieur dans un appareil gril électrique. " +
                    "Si vous n'en avez pas faites les dorer à la poêle en les retournant pour les faire dorer des deux côtés.",
            "Croque monsieur rapide",
            4, PLAT,
        )

        createAndAddRecipe(
            "Croque-Monsieur Mozarella, Avocat, Tomate",
            null,
            "${getS(string.soft_bread)}/2t;" +
                    "${getS(string.avocado)}/2;" +
                    "${getS(string.mozarella)}/1p;" +
                    "${getS(string.tomate)}/1;" +
                    getS(string.butter),
            15,
            "1. Découpez votre avocat et votre tomate en morceaux. Coupez votre boule de mozzarella en tranches.\n" +
                    "\n" +
                    "2. Chauffez votre poêle à feu moyen.\n" +
                    "\n" +
                    "3. Beurrez légèrement un côté de vos deux tranches de pain.\n" +
                    "\n" +
                    "4. Placez dans un premier temps une tranche dans la poêle.\n" +
                    "\n" +
                    "5. Par-dessus, ajoutez l’avocat, la mozzarella et les morceaux de tomate. Versez un peu d’huile d’olive et assaisonnez enfin de sel et de poivre. Refermez le tout avec l'autre tranche\n" +
                    "\n" +
                    "6. Dès que le premier côté est doré (au bout de 2-3 minutes environ), retournez et laissez cuire 3 minutes de plus.)",
            "Croque monsieur plein de saveurs !",
            4,"$PLAT/$ENTREE"
        )

        createAndAddRecipe(
            "Tomate courgette et oeufs",
            null,
            "${getS(string.courgette)}/1;${getS(string.tomate)}/2;${getS(string.egs)}/2",
            30,
            "- Laver les légumes\n" +
                    "- Éplucher la courgette partiellement (faire des \"rayures\")\n" +
                    "- Couper la courgette en rondelles puis chaques rondelles en quarts\n" +
                    "- Couper les tomates en tranches puis chaques tranches en deux\n" +
                    "\n" +
                    "- Mettre un peu d'huile dans la poêle\n" +
                    "- Faire revenir la courgette dans la poêle jusqu'à ce qu'elle soit dorée\n" +
                    "- Mettre les tomates dans la poêle avec les courgettes\n" +
                    "\n" +
                    "- Une fois que les tomates se détachent de leur peaux ajouter les oeufs sur les courgettes et les tomates (comme pour des oeufs au plat)\n" +
                    "- Saler et poivrer selon les préférences\n" +
                    "- Mettre le couvercle sur la poêle\n" +
                    "\n" +
                    "- Servir une fois les oeufs cuits",

            "Plat chaud et rapide",
            4, PLAT,
        )

        createAndAddRecipe(
            "Soupe aux pommes de terre, poireaux et champignons",
            null,
            "${getS(string.pomme_de_terre)}/800g;" +
                    "${getS(string.poireau)}/250g;" +
                    "${getS(string.champignons_de_Paris)}/150g;" +
                    "${getS(string.thyme)}/2b;" + //brins
                    "${getS(string.corn_flour)}/15g;",  //pincé
            35,
            "Pelez et coupez en dés les pommes de terre. Nettoyez et émincez grossièrement les poireaux.\n"
                    + "Nettoyez et coupez en 4 les champignons. Ajoutez les légumes dans la cocotte.\n" +
                    "Recouvrez d'eau. Salez. Fermez la cocotte.\n" +
                    "Laissez cuire 20 min, à partir de la mise en rotation de la soupape.\n" +
                    "Une fois les légumes cuits, ôtez le thym.\n" +
                    "Puis, mixez le tout.\n" +
                    "Diluez la maïzena dans un fond d'eau.\n" +
                    "Ajoutez à la soupe.\n" +
                    "Laissez épaissir à votre convenance (facultatif).\n" +
                    "\n" +
                    "ASTUCES\n" +
                    "Une fois la soupape en rotation, baissez légèrement le feu. Vous pouvez selon vos goûts, ajouter un peu de crème fraîche et poivrez.",
            "Une soupe excellente en hiver",
            4, PLAT
        )

        createAndAddRecipe("Galettes de courgettes et de maïs", null, "${getS(string.corn)}/250g;" +
                "${getS(string.courgette)}/350g;" +
                "${getS(string.cumin)}/1c" +
                "${getS(string.rice_flour)}/100g",
        25,
        "1. Lavez et râpez les courgettes. Déposez-les dans un saladier avec le maïs égoutté.\n" +
                "2. Ajoutez les épices et la farine de riz. Salez et poivrez. Mélangez bien en appuyant sur les courgettes pour libérer l’eau, qu’il faut conserver car elle servira de liant à la pâte. Laissez la pâte reposer pendant une demie-heure pour que les légumes s’imprègnent des épices.\n" +
                "3. Faites chauffer un filet d’huile d’olive dans une poêle sur feu moyen. Déposez des louches de pâte dans la poêle et faites-les cuire 2 à 3 minutes de chaque côté. Servez chaud avec une sauce au yaourt ou du guacamole.",
        "Croustillantes à l’extérieur et moelleuses au centre, idéales pour cuisiner les premières courgettes de la saison !",
        4, "$PLAT/$ENTREE")

        createAndAddRecipe(
            "Club feta, épinard et aneth",
            null,
            "${getS(string.rye_bread)}/8t;" +
                    "${getS(string.feta_cheese)}/250g;" +
                    "${getS(string.epinard)}/75g;" +
                    "${getS(string.dill)}/0.5b;" + // b = bouquet
                    "${getS(string.white_radish)}/125g;",
            35,
            "1.Préchauffez le four à 180 °C (th. 6).\n" +
                    "\n" +
                    "2.Mixez la feta et le cream cheese avec 1 cuil. à café de sel.\n" +
                    "3.Rincez et essorez les épinards et l’aneth. Rincez et épluchez le radis blanc. Coupez les épinards en lamelles, le radis blanc en fines tranches et émincez l’aneth.\n" +
                    "4.Badigeonnez d’huile d'olive un côté des tranches de pain de seigle. Disposez-en 4 côté huilé sur une plaque de cuisson. Tartinez-les de préparation au fromage et garnissez-les d’épinards, de radis blanc et d’aneth. Fermez les sandwichs avec les tranches de pain restantes, côté huilé vers l’extérieur, et parsemez-les de fleur de sel. Posez directement dessus une autre plaque de cuisson préalablement huilée, puis enfournez 20 mn.\n" +
                    "5.Servez dès la sortie du four.",
            "Un sandwich haut de gamme, équilibré et simple. Elle",
            2,"$PLAT/$ENTREE"
        )
        createAndAddRecipe(
            "Salades de pommes de terre et de saumon",
            null,
            "${getS(string.pomme_de_terre)}/800g;" +
                    "${getS(string.courgette)}/2p;" +
                    "${getS(string.salmon)}/200g;" +
                    "${getS(string.shallots)};" +
                    "${getS(string.tomate)}/2p;" + // piece
                    "${getS(string.mustard)}/1c;", // cuillere
            35,
            "1.Faites cuire les pommes de terre pelées et coupées en cubes de 4 cm de côté, 10 à 12 mn, dans une casserole d’eau salée bouillante. Passez-les ensuite sous l’eau froide pour les tiédir.\n" +
                    "2.Émincez les courgettes lavées en rondelles de 5 mm d’épaisseur. Faites-les revenir 5 mn dans de l’huile bien chaude. Débarrassez sur une assiette. Essuyez la sauteuse. Rincez les pavés de saumon et essuyez-les. Taillez-les en gros dés, 4 cm de côté, et faites-les revenir dans la sauteuse dans de l’huile bien chaude 2 à 3 mn.\n" +
                    "3.Pelez et ciselez l’échalote. Fouettez l’huile d’olive, le vinaigre et la moutarde, salez, poivrez. Coupez les tomates en dés de 1 cm. Versez l’échalote, la sauce et les dés de tomates dans un saladier. Ajoutez les pommes de terre, les courgettes et le saumon. Rectifiez l’assaisonnement si besoin et dégustez sans attendre.",
            "Délicieuse salade",
            4,"$ENTREE/$PLAT",
        )

        createAndAddRecipe("Retortilla",
            null,
            "${getS(string.pomme_de_terre)}/10;" +
                    "${getS(string.french_aubrac_cheese)}/ 400g",
            35,
            "Etape 1 \n" +
                    "Epluchez et laver les pommes de terre.\n" +
                    "Etape 2\n " +
                    "Mettez les patates à cuire sur une poele à feu moyen\n" +
                    "Etape 3\n " +
                    "Rajoutez la tome fraiche en lamelle fine sur les patates quand elles sont presque cuites\n Salez selon votre gout et dégustez !",
            "Une recette simple et excellente",
            5, PLAT,
        )
    }
    private fun getS(id: Int): String {
        return context.resources.getString(id)
    }

    private fun createListOfNewIngredients() {
        // légumes
        createAndAddIngredient(string.tomate,"Juin;Juillet;Aout")
        createAndAddIngredient(string.carotte, "Janvier;Février;Mars")
        createAndAddIngredient(string.poireau,"Janvier;Février;Mars")
        createAndAddIngredient(string.champignons_de_Paris, null, "fruits et légumes")
        createAndAddIngredient(string.oignon, null)
        createAndAddIngredient(string.courgette, "${string.may};${string.june};${string.july};${string.august};${string.september};${string.october}")
        createAndAddIngredient(string.red_bell_pepper, "${string.june};${string.july}")
        createAndAddIngredient(string.avocado, null)
        createAndAddIngredient(string.green_lentils, "fruits et légumes")
        createAndAddIngredient(string.pomme_de_terre, null)
        createAndAddIngredient(string.kiwi, "Janvier;Février;Mars")
        createAndAddIngredient(string.pomme, "Janvier;Février;Mars")
        createAndAddIngredient(string.poire, "Janvier;Février;Mars")
        createAndAddIngredient(string.shallots,"Janvier;Février;Mars")
        createAndAddIngredient(string.white_radish, null, "fruits et légumes")
        createAndAddIngredient(string.epinard, "${string.march}", "fruits et légumes")
        createAndAddIngredient(string.corn)

        //viandes , poissons , oeufs
        createAndAddIngredient(string.white_ham, null)
        createAndAddIngredient(string.egs,null, "viands, poissons, oeufs")
        createAndAddIngredient(string.sausage,null, "viandes, poissons, oeufs")
        createAndAddIngredient(string.salmon)
        createAndAddIngredient(string.ham)

        // produits laitiers
        createAndAddIngredient(string.mozarella, null)
        createAndAddIngredient(string.butter, null, "produits laitiers")
        createAndAddIngredient(string.milk, null, "produits laitiers")
        createAndAddIngredient(string.french_aubrac_cheese)
        createAndAddIngredient(string.emmental)

        // féculents
        createAndAddIngredient(string.noodles, null)
        createAndAddIngredient(string.white_bread, null)
        createAndAddIngredient(string.soft_bread)
        createAndAddIngredient(string.corn_flour,null)
        createAndAddIngredient(string.bread, null, "féculents")
        createAndAddIngredient(string.wheat_flour, null, "féculents")
        createAndAddIngredient(string.pasta, null, "féculents")
        createAndAddIngredient(string.rye_bread, null, "féculents")
        createAndAddIngredient(string.rice_flour)

        // condiments
        createAndAddIngredient(string.pepper, null)
        createAndAddIngredient(string.oil, null)
        createAndAddIngredient(string.soya_sauce, null)
        createAndAddIngredient(string.salt,null, "condiments")
        createAndAddIngredient(string.thyme, null, "condiments")
        createAndAddIngredient(string.sugar, null, "produits sucré")
        createAndAddIngredient(string.mustard, null, "condiment")
        createAndAddIngredient(string.vinegar, null, "condiment")
        createAndAddIngredient(string.dill, null, "condiment")
        createAndAddIngredient(string.cumin)


    }

    private fun createAndAddIngredient(ingredientId: Int, month: String? = null, category: String? = null) {
        listOfIngredients.add(Ingredients(name = getS(ingredientId), month = month, category = category))
    }

    private fun createAndAddRecipe(
        title: String,
        picture: Int?,
        ingredientsAndQuantities: String,
        preparationTimeInMin: Int,
        preparationStep: String,
        ltlDescription: String,
        personNumber: Int,
        recipeType: String,
    ){
        listOfRecipes.add(Recipe(
            title = title,
            picture = picture,
            ingredientsAndQuantities = ingredientsAndQuantities,
            preparationTimeInMin = preparationTimeInMin,
            preparationStep = preparationStep,
            ltlDescription = ltlDescription,
            type = recipeType,
            personNumber = personNumber
        ))
    }
}