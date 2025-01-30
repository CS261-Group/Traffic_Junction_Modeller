
$(() => {
    // when the number of lanes changes, we must truncate or generate the other lanes
    // dynamically
    for (const direction of ["n", "e", "s", "w"]) {
        const dropdownIn = `#${direction}numlanesin`;
        const listIn = `#${direction}inlanes`;
        $(dropdownIn).on("change", () => {
            const numLanes = Number.parseInt($(`${dropdownIn} option:selected`).val());
            console.log(numLanes);
    
            // remove all previous
            $(listIn).empty();
    
            // add new lanes
            for (let i = 0; i < numLanes; i++) {
                $(listIn).append(`<li class="laneconf">Lane #${i + 1}</li>`);
            }
        });

        const dropdownOut = `#${direction}numlanesout`;
        const listOut = `#${direction}outlanes`;
        $(dropdownOut).on("change", () => {
            const numLanes = Number.parseInt($(`${dropdownOut} option:selected`).val());
            console.log(numLanes);
    
            // remove all previous
            $(listOut).empty();
    
            // add new lanes
            for (let i = 0; i < numLanes; i++) {
                $(listOut).append(`<li class="laneconf">Lane #${i + 1}</li>`);
            }
        });
    }

    // force dropdown onchange event to generate list of lanes
    $("#nnumlanesin").trigger("change");
    $("#nnumlanesout").trigger("change");

    $("#npedcrossing").on("change", () => {
        const checked = $("#npedcrossing").is(":checked");
        if (!checked) {
            $("#neditcrossing").attr("disabled", true);
        } else {
            $("#neditcrossing").removeAttr("disabled");
        }
    });

    $("#npedcrossing").trigger("change");

    $("#neditcrossing").on('click', (e) => {
        e.preventDefault();
        // TODO: pop up
        console.log("EDIT CROSSING POPUP");
    });

    $("#nedittrafficlight").on('click', (e) => {
        e.preventDefault();
        // TODO: pop up
        console.log("EDIT TRAFFIC LIGHT POPUP");
    });
    
});